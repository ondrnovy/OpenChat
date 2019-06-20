package com.ondrnovy.open.chat.other

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object Permissions {



    val READ_SMS = 1



    private fun hasPermission(context: Context, permission: String): Boolean{
       return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }




    fun canReadSms(context: Context): Boolean{
        return hasPermission(context, Manifest.permission.READ_SMS)
    }


    fun requestReadSms(activity: Activity){
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_SMS), READ_SMS)
    }






}