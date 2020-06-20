package com.ng.ngbaselib.utils

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.ng.ngbaselib.R

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @date: 2020/03/27
 * Time: 18:21
 */
object ColorUtil {

    fun getColor(context: Context,color:Int): Int {
        val defaultColor = ContextCompat.getColor(context!!,color)
        var colorTheme: Int by SPreference("color", defaultColor)
        return if (colorTheme != 0 && Color.alpha(colorTheme) != 255) {
            defaultColor
        } else {
            colorTheme
        }
    }
}