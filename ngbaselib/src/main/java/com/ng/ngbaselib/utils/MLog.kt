package com.ng.ngbaselib.utils

import android.text.TextUtils
import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * 描述:
 * @author Jzn
 * @date 2020/6/19
 */
object MLog {

    private val TAG = "nangua"

    fun d(str: String) {
        str.let {
            Log.d(TAG, str)
        }
    }

    fun printLine(tag: String, isTop: Boolean) {
        if (isTop) {
            Log.w(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════")
        } else {
            Log.w(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════")
        }
    }
}