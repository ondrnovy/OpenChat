package com.ondrnovy.open.chat.model.messageLoader

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.net.Uri
import android.provider.Telephony
import com.ondrnovy.open.chat.MyApplication
import com.ondrnovy.open.chat.model.Contact
import com.ondrnovy.open.chat.model.Message
import com.ondrnovy.open.chat.model.MessageWithContactDao
import com.ondrnovy.open.chat.other.Logging
import java.util.*


class ContentProviderMessageLoader() : MessageLoader() {



    override suspend fun loadMessages(messageWithContactDao: MessageWithContactDao): Boolean {
        super.loadMessages(messageWithContactDao)


        try {

            val reqCols = arrayOf(Telephony.Sms._ID, Telephony.Sms.ADDRESS, Telephony.Sms.DATE, Telephony.Sms.BODY, Telephony.Sms.READ, Telephony.Sms.SUBJECT, Telephony.Sms.TYPE, Telephony.Sms.THREAD_ID)
            val cursor = MyApplication.instance.contentResolver.query(
                Telephony.Sms.CONTENT_URI, reqCols, null, null, null)


            cursor?.let {
                try {
                    if (it.moveToFirst()) {
                        do {

                            val date = Date(it.getLong(it.getColumnIndex(Telephony.Sms.DATE))) // ms timestamp
                            val text = it.getString(it.getColumnIndex(Telephony.Sms.BODY))
                            val type = it.getInt(it.getColumnIndex(Telephony.Sms.TYPE))
                            //val threadId = it.getLong(it.getColumnIndex(Telephony.Sms.THREAD_ID))
                            //val id = it.getLong(it.getColumnIndex(Telephony.Sms._ID))
                            //val address = it.getString(it.getColumnIndex(Telephony.Sms.ADDRESS))

                            var message = Message(text = text, isOut = type != 1, contactId = mEmptyContactId, created = date)
                            messageWithContactDao.insert(message)

                        } while (it.moveToNext())
                    }
                } finally {
                    it.close()
                }

            }

        }
        catch (e: Exception){
            Logging.exception(e)
        }



        return true
    }


}