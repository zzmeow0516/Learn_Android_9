package com.example.learn_android_9

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : BaseActivity() {

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

        val buttonSendBroadcast = findViewById<Button>(R.id.button_sendMyBroadcast)
        buttonSendBroadcast.setOnClickListener {
            //用到的构造方法是public Intent(String action) {  setAction(action) }
            val intent = Intent("com.example.learn_android_9.HEADSET_STATE_ACTION")
            //实际调用getPackage(),setPackage用于传入包名，
            //为什么要传入包名，因为我们的HeadsetReceiver是静态注册的，他不能够接收大部分隐式广播
            //我们自定义的广播正好就是隐式广播
            //所以要通过intent.setPackage指定要将广播发给哪一个app，让自定义广播变成显式广播
            intent.setPackage(packageName)
            sendOrderedBroadcast(intent, null)
        }

        val buttonForceOffline = findViewById<Button>(R.id.button_forceOffline)
        buttonForceOffline.setOnClickListener() {
            val intent = Intent("com.example.learn_android_9.FORCE_OFFLINE")
            sendBroadcast(intent)
        }

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