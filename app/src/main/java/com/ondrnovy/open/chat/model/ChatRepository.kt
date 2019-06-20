package com.ondrnovy.open.chat.model

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.ondrnovy.open.chat.model.messageLoader.ContentProviderMessageLoader

class ChatRepository(private val messageWithContactDao: MessageWithContactDao) {

    val allMessagesWithContact: LiveData<List<MessageWithContact>> = messageWithContactDao.getAllMessagesWithContact()
    val allMessages: LiveData<List<Message>> = messageWithContactDao.getAllMessages()


    @WorkerThread
    suspend fun loadTelephonyMessages(){
        ContentProviderMessageLoader().loadMessages(messageWithContactDao)
    }
}