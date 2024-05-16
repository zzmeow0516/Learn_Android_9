package com.example.learn_android_9

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class HeadsetReceiver : BroadcastReceiver() {

    private val TAG = "mylog_HeadsetReceiver"
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "received in HeadsetReceiver !", Toast.LENGTH_SHORT).show()
        Log.v(TAG, "received in HeadsetReceiver !")
        abortBroadcast()
    }
}