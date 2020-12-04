package com.ng.ngbaselib.show.frag.http.data

import com.ng.ngbaselib.R
import com.ng.ngbaselib.http.api.SearchService
import com.ng.ngbaselib.http.bean.SearchResult
import com.ng.ngbaselib.http.RetrofitClient
import com.ng.ngbaselib.network.BaseResult
import okhttp3.ResponseBody

class SearchNetWork {

    private val mService by lazy { RetrofitClient.getInstance().create(SearchService::class.java) }


    suspend fun getSearchResult(key: String): SearchResult =
        mService.getSearchResult(key,1)

    companion object {
        @Volatile
        private var netWork: SearchNetWork? = null

        fun getInstance() = netWork ?: synchronized(this) {
            netWork ?: SearchNetWork().also { netWork = it }
        }
    }




}