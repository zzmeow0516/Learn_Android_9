package com.example.learn_android_9

import android.app.Activity
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

//object用于声明单例类，这个类只需要一个实例，初始化后会一直全局存在.
object ActivityCollector: AppCompatActivity() {

    private val TAG = "mylog_ActivityCollector"

     private val activityCollector = ArrayList<Activity>()
     fun addActivity(activity: Activity) {
        activityCollector.add(activity)
        Log.v(TAG, "add activity: $activity")
    }

     fun removeActivity(activity: Activity) {
        activityCollector.remove(activity)
        Log.v(TAG, "remove activity: $activity")
    }

     fun clearActivity() {
         for (activity in activityCollector) {
             if (!activity.isFinishing) {
                 activity.finish()
             }
         }
        activityCollector.clear()
        Log.v(TAG, "clear all activity")
    }
}

//kotlin中 class 和 fun 默认是 final的, 如果需要被继承，就用open修饰
open class BaseActivity: AppCompatActivity() {

    lateinit var receiver: ForceOfflineReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.example.learn_android_9.FORCE_OFFLINE")
        receiver = ForceOfflineReceiver()
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    inner class ForceOfflineReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            //这边写接收到 强制下线 广播后的逻辑
            AlertDialog.Builder(context).apply {
                setTitle("FBI WARNING")
                setMessage("you are forced to offline!")
                setCancelable(false)
                setPositiveButton("OK") { _, _ ->
                    ActivityCollector.clearActivity()
                    val intent1 = Intent(context, LoginActivity::class.java)
                    startActivity(intent1)
                }
                show()
            }
        }
    }
}