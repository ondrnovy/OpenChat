package com.ondrnovy.open.chat

import com.facebook.stetho.Stetho
import android.app.Application
import android.content.ContentResolver


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance=this
        Stetho.initializeWithDefaults(this)
    }



    companion object {
        lateinit var instance: MyApplication
    }
}