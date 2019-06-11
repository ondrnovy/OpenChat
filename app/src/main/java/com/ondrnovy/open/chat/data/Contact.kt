package com.ondrnovy.open.chat.data

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int,

    val photo: Uri?,
    val displayName: String?,

    val phone: String?,
    val email: String?
)