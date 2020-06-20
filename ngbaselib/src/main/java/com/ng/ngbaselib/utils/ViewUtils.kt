package com.ng.ngbaselib.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

/**
 * 描述:
 * @author Jzn
 * @date 2020/6/20
 */
object ViewUtils {
    fun dpToPx(v: Float): Int {
        val resource = Resources.getSystem()
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, resource.displayMetrics).toInt()
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}