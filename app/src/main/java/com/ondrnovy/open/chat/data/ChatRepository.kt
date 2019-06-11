package com.ondrnovy.open.chat.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ChatRepository(private val messageWithContactDao: MessageWithContactDao) {

    val allMessagesWithContact: LiveData<List<MessageWithContact>> = messageWithContactDao.getAllMessagesWithContact()
    val allMessages: LiveData<List<Message>> = messageWithContactDao.getAllMessages()
}