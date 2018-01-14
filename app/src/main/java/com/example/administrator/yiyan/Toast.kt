package com.example.administrator.yiyan

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.time.Duration

/**
 * Created by Administrator on 2018/1/14/014.
 */
fun Context.toast(message: CharSequence,duration: Int=Toast.LENGTH_SHORT){
    Toast.makeText(this, message, duration).show()
}
fun Context.longToast(message: CharSequence){
    toast(message,Toast.LENGTH_LONG)
}
fun Context.log(message: String,tag: String=this.packageName){
        Log.d(tag,message)
}
fun Activity.hideKeyboard(): Boolean {
    val view = currentFocus
    view?.let {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS)
    }
    return false
}

