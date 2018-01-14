package com.example.administrator.yiyan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.attempt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    lateinit var alarmUtils: AlarmUtils
    private fun init() {
        alarmUtils = AlarmUtils(this)
        btn_time.setOnClickListener{
            val millis = et_time.text.toString().toLong()
            hideKeyboard();alarmUtils.setTime(millis)}
        tv_fresh.setOnClickListener { alarmUtils.update() }
    }
}
