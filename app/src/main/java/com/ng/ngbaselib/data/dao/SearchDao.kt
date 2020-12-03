package com.ng.ngbaselib.data.dao

import androidx.room.*
import com.ng.ngbaselib.http.bean.SearchItem

/**
 * 描述:
 * @author Jzn
 * @date 2020/7/7
 */
@Dao
interface SearchDao {

    @Query("SELECT * FROM SEARCH")
    suspend fun getSearchHistory(): List<SearchItem>?

    @Insert()
    suspend fun insertSearchHistory(searchBeans: SearchItem)

    @Query("DELETE FROM SEARCH")
    suspend fun cleanSearchHistory()

    @Update
    suspend fun updateSearchHistory(searchBean: SearchItem)

}