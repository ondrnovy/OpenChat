package com.ondrnovy.open.chat.model

import android.content.Context
import android.net.Uri
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ondrnovy.open.chat.model.messageLoader.ContentProviderMessageLoader
import com.ondrnovy.open.chat.model.messageLoader.TestMessageLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Message::class, Contact::class), version = 5)
@TypeConverters(Converters::class)
public abstract class ChatDatabase : RoomDatabase() {

    abstract fun messageWithContactDao(): MessageWithContactDao



    companion object {
        @Volatile
        private var INSTANCE: ChatDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ChatDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatDatabase::class.java,
                    "chat_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}