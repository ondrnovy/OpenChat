package com.ondrnovy.open.chat.view

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ondrnovy.open.chat.other.Logging
import com.ondrnovy.open.chat.other.Permissions
import com.ondrnovy.open.chat.viewModel.ConversationViewModel


open class SuperActivity : AppCompatActivity(){

    protected lateinit var conversationViewModel: ConversationViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        Logging.activityLifecycle(javaClass.simpleName, " onCreate")
        super.onCreate(savedInstanceState)

        conversationViewModel = ViewModelProviders.of(this).get(ConversationViewModel::class.java)
        requestPermissionsIfNeeded()
    }

    override fun onStart() {
        Logging.activityLifecycle(javaClass.simpleName, " onStart")
        super.onStart()
    }

    override fun onRestart() {
        Logging.activityLifecycle(javaClass.simpleName, " onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Logging.activityLifecycle(javaClass.simpleName, " onResume")
        super.onResume()
    }

    override fun onPause() {
        Logging.activityLifecycle(javaClass.simpleName, " onPause")
        super.onPause()
    }

    override fun onStop() {
        Logging.activityLifecycle(javaClass.simpleName, " onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Logging.activityLifecycle(javaClass.simpleName, " onDestroy")
        super.onDestroy()
    }








    fun requestPermissionsIfNeeded(){

        if (!Permissions.canReadSms(this))
            Permissions.requestReadSms(this)
        else
            conversationViewModel.onGotReadPermission()



    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray){


        if (requestCode == Permissions.READ_SMS) {
            if (Permissions.canReadSms(this))
                conversationViewModel.onGotReadPermission()
        }
    }







}