package com.ng.ngbaselib

import android.app.Application

/**
 * 描述:
 *
 * 功能模块
 * 0.权限判断
 * 1.网络访问
 * 2.recyclerview多布局
 * 3.底部tab按钮
 * 4.viewpager绑定
 * 5.viewpager+fragment
 * 6.表单提交
 * 7.表单编辑
 * 8.全局异常
 * 9.文件下载
 *
 * 组件模块
 * 1.baseactivity
 * 2.数据绑定，普通，listadapter
 * 3.网络请求
 * 4.消息通信
 * 5.文件下载
 * 6.数据存储，sp，room
 * @author Jzn
 * @date 2020/6/19
 */
open class BaseApplication : Application() {
    companion object {
        lateinit var instance : BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}