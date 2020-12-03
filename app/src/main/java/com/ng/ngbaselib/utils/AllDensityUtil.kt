package com.ng.ngbaselib.utils;

import android.app.Application
import android.content.ComponentCallbacks
import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt


object AllDensityUtil {
    fun dip2px(context: Context, dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    fun dip2pxFloat(context: Context, dipValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dipValue * scale + 0.5f
    }

    fun spTopx(context: Context, spVal: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.resources.displayMetrics).toInt()
    }

    fun realSystemPx(context: Context, spVal: Float): Int {
        if (0f != sNonCompatDensity && 0f != context.resources.displayMetrics.density) {
            return (sNonCompatDensity * spVal / context.resources.displayMetrics.density).roundToInt();
        }
        return spVal.toInt();
    }

    //density
    private var sNonCompatDensity = 0f

    //字体的缩放因子，正常情况下和density相等，但是调节系统字体大小后会改变这个值
    private var sNonCompatScaledDensity = 0f

    //字节屏幕适配方案
    fun setCustomDensity(activity: AppCompatActivity, application: Application) {
        val metrics: DisplayMetrics = application.resources.displayMetrics
        if (0f == sNonCompatDensity) {
            sNonCompatDensity = metrics.density
            sNonCompatScaledDensity = metrics.scaledDensity
            application.registerComponentCallbacks(object : ComponentCallbacks {
                override fun onConfigurationChanged(newConfig: Configuration) {
                    if (null != newConfig && newConfig.fontScale > 0) {
                        sNonCompatScaledDensity = application.resources.displayMetrics.scaledDensity
                    }
                }

                override fun onLowMemory() {}
            })
        }


        var maxDensity = metrics.widthPixels
        if (maxDensity < metrics.heightPixels) {
            maxDensity = metrics.heightPixels
        }
        //todo 这里要换成设计稿宽度
        val targetDensity =
                maxDensity / 1112f
        val targetScaledDensity: Float = targetDensity * (sNonCompatScaledDensity / sNonCompatDensity)
        val targetDensityDpi = (160 * targetDensity).toInt()
        metrics.density = targetDensity
        metrics.scaledDensity = targetScaledDensity
        metrics.densityDpi = targetDensityDpi
        val activityMetrics = activity.resources.displayMetrics
        activityMetrics.density = targetDensity
        activityMetrics.scaledDensity = targetScaledDensity
        activityMetrics.densityDpi = targetDensityDpi

    }
}