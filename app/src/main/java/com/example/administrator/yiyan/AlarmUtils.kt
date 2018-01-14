package com.example.administrator.yiyan

import android.app.AlarmManager
import android.content.Context
import android.content.Intent

/**
 * Created by Administrator on 2018/1/14/014.
 */
class AlarmUtils(var context: Context){

    fun setTime(millis: Long): Unit {
        val intent = Intent()
        intent.action = "com.mAppWidgetServiceReceiver"
        intent.putExtra("Time",millis)
        context.sendBroadcast(intent)
    }
    fun cancle(){
        val intent = Intent()
        intent.action = "com.mAppWidgetServiceReceiver"
        intent.putExtra("Cancle",0x12)
        context.sendBroadcast(intent)
    }

    fun update(): Unit {
        val intent = Intent(context,mAppWidget::class.java)
        intent.action = "android.appwidget.provider"
        context.sendBroadcast(intent)
    }

}