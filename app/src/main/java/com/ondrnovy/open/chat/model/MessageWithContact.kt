package com.ondrnovy.open.chat.model


import androidx.room.Embedded


// Is not shown in LiveData if Contact is null

data class MessageWithContact(
    @Embedded val message: Message,
    @Embedded val contact: Contact
)


