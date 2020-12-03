package com.ng.ngbaselib.show.frag.http.data

import com.ng.ngbaselib.BaseModel
import com.ng.ngbaselib.data.dao.SearchDao
import com.ng.ngbaselib.http.bean.SearchItem
import com.ng.ngbaselib.http.bean.SearchResult
import com.ng.ngbaselib.network.BaseResult
import okhttp3.ResponseBody

class SearchRepository private constructor(
    private val netWork: SearchNetWork,
    private val localData: SearchDao
) : BaseModel() {


    suspend fun getSearchHistory(): List<SearchItem>? {
        return localData.getSearchHistory()
    }

    suspend fun addSearchHistory(searchResult: SearchItem) {
        return localData.insertSearchHistory(searchResult)
    }

    suspend fun cleanSearchHistory() {
        return localData.cleanSearchHistory()
    }

    suspend fun getSearchResult(key: String): SearchResult = netWork.getSearchResult(key)

    companion object {

        @Volatile
        private var INSTANCE: SearchRepository? = null

        fun getInstance(netWork: SearchNetWork, searchDao: SearchDao) =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: SearchRepository(netWork, searchDao).also { INSTANCE = it }
                }
    }
}