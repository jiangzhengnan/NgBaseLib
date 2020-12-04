package com.ng.ngbaselib.expand

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View

/**
 * 描述:  UI层扩展函数
 *
 * todo
 * 增加更多的:
 * https://blog.csdn.net/zengke1993/article/details/107078876/
 *
 * @author Jzn
 * @date 2020/12/4
 */
object UIExpand {
    //view的扩展
    fun <T : View> T.click(block: (T) -> Unit) {
        setOnClickListener {
            block(this)
        }
    }
    //私有扩展属性，允许2次点击的间隔时间
    private var <T : View> T.delayTime: Long
        get() = getTag(0x7FFF0001) as? Long ?: 0
        set(value) {
            setTag(0x7FFF0001, value)
        }
    //私有扩展属性，记录点击时的时间戳
    private var <T : View> T.lastClickTime: Long
        get() = getTag(0x7FFF0002) as? Long ?: 0
        set(value) {
            setTag(0x7FFF0002, value)
        }
    //私有扩展方法，判断能否触发点击事件
    private fun <T : View> T.canClick(): Boolean {
        var flag = false
        var now = System.currentTimeMillis()
        if (now - this.lastClickTime >= this.delayTime) {
            flag = true
            this.lastClickTime = now
        }
        return flag
    }
    //扩展点击事件，默认 500ms 内不能触发 2 次点击
    fun <T : View> T.clickWithDuration(time: Long = 500, block: (T) -> Unit) {
        delayTime = time
        setOnClickListener {
            if (canClick()) {
                block(this)
            }
        }
    }

    //Context的扩展
    //使用内联函数的泛型参数 reified 特性来实现
    inline fun <reified T : Activity> Context.startActivity() {
        val intent = Intent(this, T::class.java)
        if (this !is Activity) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }
    //屏幕宽度(px)
    inline val Context.screenWidth: Int
        get() = resources.displayMetrics.widthPixels

    //屏幕高度(px)
    inline val Context.screenHeight: Int
        get() = resources.displayMetrics.heightPixels

    //屏幕的密度
    inline val Context.density: Float
        get() = resources.displayMetrics.density

    //dp 转为 px
    inline fun Context.dp2px(value: Int): Int = (density * value).toInt()

    //dp 转为 px
    inline fun Context.dp2px(value: Float): Int = (density * value).toInt()

    //px 转为 dp
    inline fun Context.px2dp(value: Int): Float = value.toFloat() / density

}