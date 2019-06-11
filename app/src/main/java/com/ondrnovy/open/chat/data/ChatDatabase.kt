package com.ondrnovy.open.chat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Message::class, Contact::class), version = 1)
@TypeConverters(Converters::class)
public abstract class ChatDatabase : RoomDatabase() {

    abstract fun messageWithContactDao(): MessageWithContactDao




    private class ChatDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {


        suspend fun populateDatabaseWithTestData(messageWithContactDao: MessageWithContactDao) {
            var message = Message(text = "Ahoj", isOut = true)
            messageWithContactDao.insert(message)

            message = Message(text = "Jak se kurva máš", isOut = true)
            messageWithContactDao.insert(message)

            message = Message(text = "Blbě", isOut = false)
            messageWithContactDao.insert(message)
        }


        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabaseWithTestData(database.messageWithContactDao())
                }
            }
        }


        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

        }

    }

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
                ).addCallback(
                    ChatDatabaseCallback(
                        scope
                    )
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}