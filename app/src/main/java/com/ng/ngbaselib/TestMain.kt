package com.ng.ngbaselib

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * 描述:
 * @author Jzn
 * @date 2020/12/2
 */
fun main(args: Array<String>) {


    runBlocking {
        productor().collect {
            delay(1000)
            println("custom " + it +" " + System.currentTimeMillis())
        }
    }
    print("result")

}


  fun productor() = channelFlow<Int> {
    for (  i in 0..10) {
        delay(2000)
        println("produc $i" +" " + System.currentTimeMillis())
        send(i)
     }

}



