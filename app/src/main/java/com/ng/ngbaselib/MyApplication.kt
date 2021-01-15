package com.ng.ngbaselib

import android.app.Application
import android.content.Intent
import com.alibaba.android.arouter.launcher.ARouter
import com.ng.ngbaselib.record.RecordService

/**
 * 功能模块:
 * 权限判断👌
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
 * BaseActivity
 * BaseFragment
 * 数据绑定，普通，listAdapter
 * 网络请求
 * 消息通信
 * 文件下载
 * 数据存储sp👌room
 *
 * 去rxjava 化，采用livedata
 * @author Jzn
 * @date 2020/6/19
 */
open class MyApplication : Application() {
    companion object {
        lateinit var instance : MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化


        // 启动 Marvel service
        // 启动 Marvel service
        //startService(Intent(this, RecordService::class.java))
    }
}