package com.sharad.noteapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sharad.noteapplication.R
import com.sharad.noteapplication.db.Note
import com.sharad.noteapplication.viewmodel.NoteViewModal

class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {

    private lateinit var viewModal: NoteViewModal
    private lateinit var notesRV: RecyclerView
    private lateinit var addFAB: FloatingActionButton
    private lateinit var noteRVAdapter: NoteRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesRV = findViewById(R.id.notesRV)
        addFAB = findViewById(R.id.idFAB)

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)
        initRecyclerView()

        viewModal.allNotes.observe(this) {
            it?.let {
                noteRVAdapter.updateList(it)
            }
        }

        addFAB.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    private fun initRecyclerView() {
        notesRV.layoutManager = LinearLayoutManager(this)
        noteRVAdapter = NoteRVAdapter(this, this, this)
        notesRV.adapter = noteRVAdapter
    }

    override fun onDeleteIconClick(note: Note) {
        viewModal.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()

    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra(Constant.NOTE_TYPE, "Edit")
        intent.putExtra(Constant.NOTE_TILE, note.noteTitle)
        intent.putExtra(Constant.NOTE_DESCRIPTION, note.noteDescription)
        intent.putExtra(Constant.NOTE_ID, note.id)
        startActivity(intent)
        this.finish()    }
}