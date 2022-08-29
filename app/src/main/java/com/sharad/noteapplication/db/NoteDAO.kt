package com.sharad.noteapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDAO  {

    @Insert
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("select * from noteTable")
    fun getAllNotes():LiveData<List<Note>>

    @Update
    suspend fun updateNote(note: Note)
}