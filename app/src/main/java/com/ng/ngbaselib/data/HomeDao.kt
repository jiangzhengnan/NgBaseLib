package com.ng.ngbaselib.data

import androidx.room.*

/**
 *   @auther : Aleyn
 *   time   : 2020/03/12
 */
@Dao
interface HomeDao {

    @androidx.room.Query("SELECT * FROM HOME_DATA WHERE curPage = :page")
    suspend fun getHomeList(page: Int): HomeListBean?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(homeListBean: HomeListBean)

    @androidx.room.Query("DELETE FROM HOME_DATA")
    suspend fun deleteHomeAll()

    @Update
    suspend fun updataData(homeListBean: HomeListBean)

    @Delete
    suspend fun deleteData(vararg data: HomeListBean)


}