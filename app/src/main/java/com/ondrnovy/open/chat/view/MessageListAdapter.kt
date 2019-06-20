package com.ondrnovy.open.chat.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ondrnovy.open.chat.R
import com.ondrnovy.open.chat.model.MessageWithContact




class MessageListAdapter constructor(
    context: Context
) : RecyclerView.Adapter<MessageListAdapter.MessageViewHolder>() {


    private val ITEM_TYPE_OUT = 1
    private val ITEM_TYPE_IN = 2


    private val context = context
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var messages = emptyList<MessageWithContact>() // Cached copy of words

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text)
        val dateView: TextView = itemView.findViewById(R.id.date)
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val photoView: ImageView = itemView.findViewById(R.id.photo)
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].message.isOut) ITEM_TYPE_OUT else ITEM_TYPE_IN
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {

        var type:Int
        when (viewType) {
            ITEM_TYPE_OUT -> type = R.layout.message_out_item
            ITEM_TYPE_IN -> type = R.layout.message_item
            else -> {
                type = R.layout.message_item
            }
        }

        return MessageViewHolder(inflater.inflate(type, parent, false))
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
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