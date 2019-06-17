package com.ondrnovy.open.chat.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ondrnovy.open.chat.R
import com.ondrnovy.open.chat.data.Message
import com.ondrnovy.open.chat.data.MessageWithContact
import com.ondrnovy.open.chat.utils.formatDate


class MessageListAdapter constructor(
    context: Context
) : RecyclerView.Adapter<MessageListAdapter.WordViewHolder>() {

    private val context = context
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var messages = emptyList<MessageWithContact>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text)
        val dateView: TextView = itemView.findViewById(R.id.date)
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val photoView: ImageView = itemView.findViewById(R.id.photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.message_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = messages[position]
        holder.textView.text = current.message.text
        holder.dateView.text = formatDate(current.message.created)

        Glide.with(context)
            .load(current.contact.photo)
            .placeholder(R.drawable.ic_account_box_black_24dp)
            .fitCenter()
            .into(holder.photoView)


        Glide.with(context)
            .load(R.drawable.ic_account_box_black_24dp)
            .centerCrop()
            .into(holder.imageView)
    }

    internal fun setMessages(messages: List<MessageWithContact>) {
        this.messages = messages
        notifyDataSetChanged()
    }

    override fun getItemCount() = messages.size
}