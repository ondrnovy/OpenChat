package com.ondrnovy.open.chat.model

import androidx.lifecycle.LiveData
import androidx.room.*


// To get the live updates you have to make sure that the instance of Dao must be same across all operations


@Dao
interface MessageWithContactDao {
    @Query(
        "SELECT * " +
                "FROM messages LEFT JOIN contacts " +
                "WHERE contacts.id = messages.contact_id " +
                "ORDER BY messages.created DESC"
    )
    fun getAllMessagesWithContact(): LiveData<List<MessageWithContact>>



    @Query(
        "SELECT * FROM messages ORDER BY created DESC"
    )
    fun getAllMessages(): LiveData<List<Message>>


    @Query("DELETE FROM messages")
    fun deleteAllMessages()

    @Query("DELETE FROM contacts")
    fun deleteAllContacts()

    @Transaction
    fun deleteAll(){
        deleteAllMessages()
        deleteAllContacts()
    }



    @Insert
    suspend fun insert(message: Message)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact): Long

}