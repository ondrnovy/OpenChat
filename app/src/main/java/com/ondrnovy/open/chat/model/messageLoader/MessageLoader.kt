package com.ondrnovy.open.chat.model.messageLoader


import com.ondrnovy.open.chat.model.Contact
import com.ondrnovy.open.chat.model.MessageWithContactDao


open class MessageLoader {


    var mEmptyContactId = 0

    open suspend fun loadMessages(messageWithContactDao: MessageWithContactDao): Boolean {

        messageWithContactDao.deleteAll()

        val contact = Contact(displayName = "")
        mEmptyContactId = messageWithContactDao.insert(contact).toInt()

        return true
    }

}