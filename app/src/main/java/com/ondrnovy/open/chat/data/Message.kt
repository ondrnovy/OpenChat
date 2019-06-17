package com.ondrnovy.open.chat.data

import android.net.Uri
import androidx.room.*
import androidx.room.ForeignKey.SET_NULL
import java.util.*


@Entity(tableName = "messages",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Contact::class,
            onDelete = SET_NULL,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("contact_id"))
    ),
    indices = arrayOf(Index(value = ["contact_id"]))
)
data class Message(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "m_id") var id: Int = 0,

    var text: String = "",
    val image: Uri? = null,
    val isOut: Boolean,
    val created: Date = Date(),

    @ColumnInfo(name = "contact_id") val contactId: Int
)