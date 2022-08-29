package com.sharad.noteapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.sharad.noteapplication.R
import com.sharad.noteapplication.db.Note
import com.sharad.noteapplication.viewmodel.NoteViewModal
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var noteTitleEdt: EditText
    private lateinit var noteEdtDescription: EditText
    private lateinit var saveBtn: Button
    private lateinit var viewModal: NoteViewModal
    var noteID: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)

        noteTitleEdt = findViewById(R.id.tv_edit_title)
        noteEdtDescription = findViewById(R.id.tv_edit_description)
        saveBtn = findViewById(R.id.idBtn)

        val noteType = intent.getStringExtra(Constant.NOTE_TYPE)
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra(Constant.NOTE_TILE)
            val noteDescription = intent.getStringExtra(Constant.NOTE_DESCRIPTION)
            noteID = intent.getIntExtra(Constant.NOTE_ID, -1)
            saveBtn.text = "Update Note"
            noteTitleEdt.setText(noteTitle)
            noteEdtDescription.setText(noteDescription)
        } else {
            saveBtn.text = "SAVE"
        }
        saveBtn.setOnClickListener {
            val noteTitle = noteTitleEdt.text.toString()
            val noteDescription = noteEdtDescription.text.toString()

            if (noteType.equals("Edit")) {
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDateAndTime: String = sdf.format(Date())
                val updatedNote = Note(noteTitle, noteDescription, "", currentDateAndTime, true)
                updatedNote.id = noteID
                viewModal.updateNote(updatedNote)
                Toast.makeText(this, "Note Updated...", Toast.LENGTH_LONG).show()

            } else{
            val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
            val currentDateAndTime: String = sdf.format(Date())
            viewModal.addNote(Note(noteTitle, noteDescription, "", currentDateAndTime, false))
            Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
        }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
    }
        }

    }
