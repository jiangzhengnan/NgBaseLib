package com.ng.ngbaselib.show.frag.http

import androidx.lifecycle.MutableLiveData
import com.ng.ngbaselib.BaseViewModel
import com.ng.ngbaselib.MyApplication
import com.ng.ngbaselib.http.bean.SearchResult
import com.ng.ngbaselib.show.frag.http.data.SearchNetWork
import com.ng.ngbaselib.show.frag.http.data.SearchRepository
import com.ng.ngbaselib.utils.MLog
import com.ng.ngbaselib.data.MyDatabase
import com.ng.ngbaselib.http.bean.SearchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withTimeoutOrNull

/**
 * 描述:
 * @author Jzn
 * @date 2020/7/7
 */
class SearchViewModel : BaseViewModel(MyApplication.instance) {

    private val searchRepository by lazy {
        SearchRepository.getInstance(
            SearchNetWork.getInstance(),
            MyDatabase.getInstanse().searchLocalData()
        )
    }

    //得到搜索结果
    var searchResult = MutableLiveData<SearchResult>()


    fun getSearchResult(key: String): MutableLiveData<SearchResult> {
        MLog.d("getSearchResult: $key")
        launchOnlyResultForBody({
            searchRepository.getSearchResult(key)
        }, {
            MLog.d("getSearchResponse: $it")
            searchResult.value = it
        }, {
            MLog.d("getSearchResponse error: " + it.errMsg + " " + it.code)
            errorResult.value = it.errMsg + " " + it.code
        }, isShowDialog = true)
        return searchResult
    }


    fun getSearchResultWithFlow(key: String): MutableLiveData<SearchResult> {
        MLog.d("getSearchResult Flow: $key")
        launchFlow({
            searchRepository.getSearchResult(key)
        }, {
            MLog.d("getSearchResponse: $it")
            searchResult.value = it
        },
            {
                MLog.d("getSearchResponse error: " + it.message)
                errorResult.value = it.message
            }
        )
        return searchResult
    }

    //测试flow请求多个结果
    fun getSearchResultTestFlow(key: String) {
        launchUI {
            flow {
                //1
                emit(searchRepository.getSearchResult(key))
                delay(3000)
                //2
                emit(searchRepository.getSearchResult(key + key))
                delay(3000)
                //3
                emit(searchRepository.getSearchResult(key + key + key))
                delay(3000)
            }.flowOn(Dispatchers.IO)
                .onStart {
                    //显示加载框
                    defUI.showDialog.call()
                }.onCompletion {
                    defUI.dismissDialog.call()
                }.catch {
                    defUI.showError.call()
                }.collectLatest {
                    defUI.dismissDialog.call()
                    searchResult.value = it
                }
        }
    }


    //历史记录
    var historyResult = MutableLiveData<List<SearchItem>>()

    fun getSearchHistory(): MutableLiveData<List<SearchItem>> {
        launchGo({
            historyResult.value = searchRepository.getSearchHistory()
        })
        return historyResult
    }

    fun addSearchHistory(bean: SearchItem): MutableLiveData<List<SearchItem>> {
        launchGo({
            searchRepository.addSearchHistory(bean)
            MLog.d(
                "addSearchHistory结果:" + searchRepository.getSearchHistory()
                    .toString() + " " + searchRepository.getSearchHistory()?.size
            )
            historyResult.value = searchRepository.getSearchHistory()
        })
        return historyResult
    }

    fun cleanSearchHistory(): MutableLiveData<List<SearchItem>> {
        launchGo({
            searchRepository.cleanSearchHistory()
            historyResult.value = searchRepository.getSearchHistory()
        })
        return historyResult
    }
}