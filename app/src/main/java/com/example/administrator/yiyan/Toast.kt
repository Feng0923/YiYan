package com.example.administrator.yiyan

import android.content.Context
import android.widget.Toast
import java.time.Duration

/**
 * Created by Administrator on 2018/1/14/014.
 */
fun Context.toast(message: CharSequence,duration: Int=Toast.LENGTH_SHORT){
    Toast.makeText(this, message, duration).show()
}