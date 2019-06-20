package com.ondrnovy.open.chat.model.messageLoader

import android.net.Uri
import com.ondrnovy.open.chat.model.Contact
import com.ondrnovy.open.chat.model.Message
import com.ondrnovy.open.chat.model.MessageWithContactDao


class TestMessageLoader : MessageLoader() {


    override suspend fun loadMessages(messageWithContactDao: MessageWithContactDao): Boolean {
        super.loadMessages(messageWithContactDao)

        val contact = Contact(displayName = "Kokot")
        val contactId = messageWithContactDao.insert(contact).toInt()

        val photoUri = Uri.parse("http://goo.gl/gEgYUd")
        val contact2 = Contact(displayName = "Arnie", photo = photoUri)
        val contactId2 = messageWithContactDao.insert(contact2).toInt()


        var message = Message(text = "Ahoj", isOut = true, contactId = contactId2)
        messageWithContactDao.insert(message)

        message = Message(text = "Jak se kurva máš", isOut = true, contactId = contactId2)
        messageWithContactDao.insert(message)

        message = Message(text = "Blbě", isOut = false, contactId = contactId2)
        messageWithContactDao.insert(message)

        message = Message(text = "CCCC", isOut = false, contactId = contactId2)
        messageWithContactDao.insert(message)

        return true
    }


}