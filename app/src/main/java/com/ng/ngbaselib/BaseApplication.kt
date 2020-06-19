package com.ng.ngbaselib

import android.app.Application

/**
 * 功能模块:
 * 权限判断
 * 网络访问
 * recyclerview多布局
 * 底部tab按钮
 * viewpager绑定
 * viewpager+fragment
 * 表单提交
 * 表单编辑
 * 全局异常
 * 文件下载
 *
 * 组件模块:
 * baseactivity
 * 数据绑定，普通，listadapter
 * 网络请求
 * 消息通信
 * 文件下载
 * 数据存储，sp，room
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