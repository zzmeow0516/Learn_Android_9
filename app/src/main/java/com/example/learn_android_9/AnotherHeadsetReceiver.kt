package com.example.learn_android_9

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AnotherHeadsetReceiver : BroadcastReceiver() {

    private val TAG = "mylog_AnotherHeadsetReceiver"
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "received in AnotherHeadsetReceiver !", Toast.LENGTH_SHORT).show()
        Log.v(TAG, "received in AnotherHeadsetReceiver !")
    }
}