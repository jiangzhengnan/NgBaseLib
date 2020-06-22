//package com.ng.ngbaselib.show.frag
//
//import com.ng.ngbaselib.BaseModel
//import com.ng.ngbaselib.data.HomeDao
//import com.ng.ngbaselib.data.HomeListBean
//
//
//class HomeRepository private constructor(
//        private val netWork: HomeNetWork,
//        private val localData: HomeDao
//) : BaseModel() {
//
//    suspend fun getHomeList(page: Int, refresh: Boolean): HomeListBean {
//        return cacheNetCall({
//            netWork.getHomeList(page)
//        }, {
//            localData.getHomeList(page + 1)
//        }, {
//            if (refresh) localData.deleteHomeAll()
//            localData.insertData(it)
//        }, {
//            !refresh && it != null
//        })
//    }
//
//    companion object {
//
//        @Volatile
//        private var INSTANCE: HomeRepository? = null
//
//        fun getInstance(netWork: HomeNetWork, homeDao: HomeDao) =
//                INSTANCE ?: synchronized(this) {
//                    INSTANCE ?: HomeRepository(netWork, homeDao).also { INSTANCE = it }
//                }
//    }
//}