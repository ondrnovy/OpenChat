package com.ondrnovy.open.chat.other

import android.util.Log

object Logging {

    private val TAG = "openchat"

    fun exception(e:Exception){
        Log.e(TAG, e.toString())
    }



    fun activityLifecycle(activityClassName: String, method: String){
        Log.d(TAG, activityClassName + " " + method)
    }

}