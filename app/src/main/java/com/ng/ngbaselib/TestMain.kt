package com.ng.ngbaselib

import android.view.View
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

/**
 * 描述:
 * @author Jzn
 * @date 2020/12/2
 */
fun main(args: Array<String>) {
    //run(view, ui) // 最终调用
}

//饿汉式
object User{

}

//懒汉式
class User2  {
    companion object{
        val Instance:User2 by lazy {
            User2()
        }
    }

}
