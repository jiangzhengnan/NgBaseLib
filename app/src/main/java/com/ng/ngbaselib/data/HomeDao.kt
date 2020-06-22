//package com.ng.ngbaselib.data
//
//import retrofit2.http.Query
//
///**
// *   @auther : Aleyn
// *   time   : 2020/03/12
// */
//@Dao
//interface HomeDao {
//
//    @Query("SELECT * FROM HOME_DATA WHERE curPage = :page")
//    suspend fun getHomeList(page: Int): HomeListBean?
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertData(homeListBean: HomeListBean)
//
//    @Query("DELETE FROM HOME_DATA")
//    suspend fun deleteHomeAll()
//
//    @Update
//    suspend fun updataData(homeListBean: HomeListBean)
//
//    @Delete
//    suspend fun deleteData(vararg data: HomeListBean)
//
//    //Banner
//    @Query("SELECT * FROM BANNER")
//    suspend fun getBannerList(): List<BannerBean>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertBanner(banners: List<BannerBean>)
//
//    @Query("DELETE FROM BANNER")
//    suspend fun deleteBannerAll()
//}