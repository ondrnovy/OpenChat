package com.ondrnovy.open.chat.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

import com.ondrnovy.open.chat.R

import com.ondrnovy.open.chat.viewModel.ConversationViewModel


class MainActivity : SuperActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = MessageListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



        conversationViewModel.allMessageWithContact.observe(this, Observer { messages ->
            // Update the cached copy of the words in the adapter.
            messages?.let { adapter.setMessages(messages) }
        })

    }

}
