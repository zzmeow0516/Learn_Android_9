package com.example.learn_android_9

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BootCompleteReceiver : BroadcastReceiver() {

    private val TAG = "mylog_BootCompleteReceiver"

    //不要在这里进行任何耗时操作！
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "boot completed !", Toast.LENGTH_LONG).show()
        Log.v(TAG, "boot completed!")
    }
}