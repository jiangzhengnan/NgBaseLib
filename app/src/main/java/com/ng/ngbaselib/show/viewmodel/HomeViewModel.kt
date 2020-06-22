package com.ng.ngbaselib.show.viewmodel

import com.ng.ngbaselib.BaseApplication
import com.ng.ngbaselib.BaseViewModel

class HomeViewModel : BaseViewModel(BaseApplication.instance) {

    private val homeRepository by lazy {
        //InjectorUtil.getHomeRepository()
    }


//    private val projectData = MutableLiveData<HomeListBean>()


//     * @param page 页码
//     * @param refresh 是否刷新
//     */
//    fun getHomeList(page: Int, refresh: Boolean = false): MutableLiveData<HomeListBean> {
//        launchGo({
//            //projectData.value = homeRepository.getHomeList(page, refresh)
//        })
//        return projectData
//    }
}
