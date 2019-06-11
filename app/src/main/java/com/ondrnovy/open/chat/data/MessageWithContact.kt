package com.ondrnovy.open.chat.data


import androidx.room.Embedded


data class MessageWithContact(
    @Embedded val message: Message,
    @Embedded val contact: Contact?
)

