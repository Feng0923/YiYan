package com.example.administrator.yiyan

import android.content.Context
import android.content.Intent

/**
 * Created by Administrator on 2018/1/14/014.
 */
class AlarmUtils(var context: Context){

    fun setTime(millis: Long): Unit {
        verifyTime(millis)
        val intent = Intent().apply {
            action = "com.mAppWidgetServiceReceiver"
            putExtra("Time", millis)
        }
        context.sendBroadcast(intent)
    }

    private fun verifyTime(millis: Long) {
        if (millis==0L)context.toast("请输入合法时间间隔")else context.toast("success")
    }

    fun cancle(){
        val intent = Intent().apply {
            action = "com.mAppWidgetServiceReceiver"
            putExtra("Cancle",0x12)
        }
        context.sendBroadcast(intent)
    }

    fun update(): Unit {
        val intent = Intent(context,mAppWidget::class.java)
        intent.action = "android.appwidget.provider"
        context.sendBroadcast(intent)
    }

}