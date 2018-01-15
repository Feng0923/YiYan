package com.example.administrator.yiyan

import android.content.Context
import android.content.Intent

/**
 * Created by Administrator on 2018/1/14/014.
 */
class AlarmUtils(var context: Context){

    fun setTime(millis: Long): Unit {
        if (verifyTime(millis)) {
            val intent = Intent().apply {
                setClass(context,mAppWidgetService::class.java)
                action = "com.mAppWidgetServiceReceiver"
                putExtra("Time", millis)
            }
            context.startService(intent)
            context.sendBroadcast(intent)
        }
    }

    private fun verifyTime(millis: Long):Boolean {
        if (millis==0L)context.toast("请输入合法时间间隔") else context.toast("设置成功");return true
        return false
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