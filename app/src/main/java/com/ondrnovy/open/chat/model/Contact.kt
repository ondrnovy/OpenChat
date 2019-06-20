package com.ondrnovy.open.chat.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,

    val photo: Uri? = null,
    val displayName: String,

    val phone: String = "",
    val email: String = ""
)