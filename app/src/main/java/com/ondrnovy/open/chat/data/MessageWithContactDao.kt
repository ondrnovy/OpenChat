package com.ondrnovy.open.chat.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageWithContactDao {
    @Query(
        "SELECT * " +
                "FROM messages LEFT JOIN contacts " +
                "WHERE contacts.id = messages.contact_id"
    )
    fun getAllMessagesWithContact(): LiveData<List<MessageWithContact>>



    @Query(
        "SELECT * FROM messages"
    )
    fun getAllMessages(): LiveData<List<Message>>


    @Insert
    suspend fun insert(message: Message)

}