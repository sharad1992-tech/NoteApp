package com.sharad.noteapplication.repo

import androidx.lifecycle.LiveData
import com.sharad.noteapplication.db.Note
import com.sharad.noteapplication.db.NoteDAO

class NoteRepository(private val notesDao: NoteDAO) {

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.insertNote(note)
    }

    suspend fun delete(note: Note){
        notesDao.deleteNote(note)
    }

    suspend fun update(note: Note){
        notesDao.updateNote(note)
    }
}