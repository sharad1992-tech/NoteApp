package com.sharad.noteapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteTable")
class Note(
    @ColumnInfo(name = "title") val noteTitle: String,
    @ColumnInfo(name = "description") val noteDescription: String,
    @ColumnInfo(name = "url") val noteUrl: String,
    @ColumnInfo(name = "timestamp")val timeStamp :String,
    @ColumnInfo(name = "edited_note") val isNoteEdited: Boolean
) {
    @PrimaryKey(autoGenerate = true) var id = 0
}