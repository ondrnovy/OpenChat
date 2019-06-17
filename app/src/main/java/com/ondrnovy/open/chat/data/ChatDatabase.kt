package com.ondrnovy.open.chat.data

import android.content.Context
import android.net.Uri
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Message::class, Contact::class), version = 5)
@TypeConverters(Converters::class)
public abstract class ChatDatabase : RoomDatabase() {

    abstract fun messageWithContactDao(): MessageWithContactDao




    private class ChatDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {


        suspend fun populateDatabaseWithTestData(messageWithContactDao: MessageWithContactDao) {
            messageWithContactDao.deleteAll()

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
        }



        suspend fun populateDatabaseWithContentProviders(messageWithContactDao: MessageWithContactDao) {
            messageWithContactDao.deleteAll()


        }



        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    //populateDatabaseWithTestData(database.messageWithContactDao())
                    populateDatabaseWithContentProviders(database.messageWithContactDao())
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
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}