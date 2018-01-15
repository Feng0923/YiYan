package com.example.administrator.yiyan

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.RemoteViews

/**
 * Created by Administrator on 2018/1/14/014.
 */
class mAppWidgetService : Service() {
    val TAG = "mAppWidgerService"
    lateinit var alarmManager: AlarmManager
    override fun onBind(p0: Intent?): IBinder {
        TODO("asdfa")
    }

    lateinit var intent: Intent
    lateinit var pendingIntent: PendingIntent
    override fun onCreate() {
        super.onCreate()
        val filter = IntentFilter()
        filter.addAction("com.mAppWidgetServiceReceiver")
        registerReceiver(mAppWidgetServiceReceiver, filter)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as android.app.AlarmManager
        intent = Intent("android.appwidget.action.APPWIDGET_UPDATE")
        intent?.setClass(applicationContext, mAppWidget::class.java)
//        Log.d("asdasdfasd", "asdfasdf")
        pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intent, 0)
        val remote =  RemoteViews(applicationContext.packageName,R.layout.m_app_widget)
        remote.setOnClickPendingIntent(R.id.appwidget_layout,pendingIntent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startTask()
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onDestroy() {
        super.onDestroy()
        alarmManager.cancel(pendingIntent)
    }

    /**
     * state(record alarmManager's state) 0x11 : no task ; 0x12 : on task
     */
    var state: Int = 0x11
    private fun startTask(millis: Long = 3 * 1000): Unit {
//        val intent1 = Intent("android.appwidget.action.APPWIDGET_UPDATE")
//        intent1?.setClass(applicationContext, mAppWidget::class.java)
//        Log.d("asdasdfasd", "asdfasdf")
//        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intent1, 0)
        when (state) {
            0x12 -> {
                alarmManager.cancel(pendingIntent);state = 0x11
            }
        }
        val time = millis*60
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), time, pendingIntent);state = 0x12
    }
    val mAppWidgetServiceReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val data = p1?.getIntExtra("control",0x10)
            val millis = p1?.getLongExtra("Time",1*1000)
            if(millis!=null) startTask(millis)
        }
    }
}