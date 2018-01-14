package com.example.administrator.yiyan

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL
import kotlin.math.log


/**
 * Implementation of App Widget functionality.
 */
class mAppWidget : AppWidgetProvider() {
    var yiYan: String = ""
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        fresh(context)
    }
    private fun fresh(context: Context?) {
        doAsync {
            Log.d("cao","weishenme ")
            val data = URL("https://sslapi.hitokoto.cn/").readText()
            val json =JSONObject(data)
            val content = json.getString("hitokoto")
            val from = json.getString("from")
            uiThread {
                val remote = RemoteViews(context?.packageName, R.layout.m_app_widget)
                remote.setTextViewText(R.id.appwidget_text, content)
                remote.setTextViewText(R.id.from,"----$from")
                context?.log("------")
                val con = ComponentName(context, mAppWidget::class.java)
                val appWidgetManager = AppWidgetManager.getInstance(context)
                appWidgetManager.updateAppWidget(con, remote)
            }
        }
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
        context?.stopService(Intent(context,mAppWidgetService::class.java))

    }
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        context.startService(Intent(context,mAppWidgetService::class.java))
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
        context.stopService(Intent(context,mAppWidgetService::class.java))
    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {

            val views = RemoteViews(context.packageName, R.layout.m_app_widget)
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

