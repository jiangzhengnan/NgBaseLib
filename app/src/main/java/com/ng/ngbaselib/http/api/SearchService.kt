package com.ng.ngbaselib.http.api

import com.ng.ngbaselib.http.bean.SearchResult
import com.ng.ngbaselib.network.BaseResult
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * github api请求集合
 */
interface SearchService {

    /**
     * 搜索
     * https://api.github.com/search/repositories?q=pumpkin&sort=end&per_page=1
     */
    @GET("search/repositories")
    suspend fun getSearchResult(@Query("q") key: String,@Query("per_page") page:Int): SearchResult


}