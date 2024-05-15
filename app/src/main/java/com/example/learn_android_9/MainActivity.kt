package com.example.learn_android_9

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val TAG = "mylog_BroadcastReceiver"

    //需要写成类的成员变量，方便我们在onCreate和onDestroy中调用
    lateinit var timeChangeBroadcastReceiver: TimeChangeBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val intentFilter = IntentFilter()
        //系统每隔一分钟发一次TiME_TICK
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeBroadcastReceiver = TimeChangeBroadcastReceiver()
        registerReceiver(timeChangeBroadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeBroadcastReceiver)
    }

    inner class TimeChangeBroadcastReceiver() : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "time changed", Toast.LENGTH_SHORT).show()
            Log.v(TAG, "time changed")
        }
    }
}