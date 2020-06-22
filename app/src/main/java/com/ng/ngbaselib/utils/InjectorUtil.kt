package com.ng.ngbaselib.utils

import com.ng.ngbaselib.data.LinDatabase
import com.ng.ngbaselib.network.HomeNetWork
import com.ng.ngbaselib.data.HomeRepository


object InjectorUtil {

    fun getHomeRepository() = HomeRepository.getInstance(
        HomeNetWork.getInstance(),
        LinDatabase.getInstanse().homeLocaData()
    )

}