package com.ondrnovy.open.chat.data

import android.net.Uri
import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }


    @TypeConverter
    fun stringToUri(value: String?): Uri? {
        return value?.let { Uri.parse(value) }
    }

    @TypeConverter
    fun uriToString(uri: Uri?): String? {
        return uri?.let { it.toString() }
    }
}