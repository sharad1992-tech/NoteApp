package com.sharad.noteapplication.ui

import android.content.Context
import android.widget.Toast

object  Constant {

    const val NOTE_TYPE = "note_type"
    const val NOTE_ID = "note_id"
    const val NOTE_TILE = "note_tile"
    const val NOTE_DESCRIPTION = "note_description"

    fun Context.toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}