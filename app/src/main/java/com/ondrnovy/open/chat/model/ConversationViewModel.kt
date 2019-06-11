package com.ondrnovy.open.chat.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ondrnovy.open.chat.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConversationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ChatRepository
    val allMessages: LiveData<List<Message>>

    init {
        val messageWithContactDao = ChatDatabase.getDatabase(application, viewModelScope).messageWithContactDao()
        repository = ChatRepository(messageWithContactDao)
        allMessages = repository.allMessages
    }

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        //repository.insert(word)
    }
}
