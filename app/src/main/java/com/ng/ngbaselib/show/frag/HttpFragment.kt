package com.ng.ngbaselib.show.frag

import android.view.View
import com.ng.ngbaselib.BaseFragment
import com.ng.ngbaselib.R
import kotlinx.android.synthetic.main.fragment_http.*

/**
 * 描述:
 * @author Jzn
 * @date 2020/6/20
 */
class HttpFragment: BaseFragment() {
    //得到随机小猫猫图片，get请求
    private val HTTP_CAT = "https://api.thecatapi.com/v1/images/search"


    private fun getCat() {


    }

    override fun initViewsAndEvents(v: View?) {
        btn_1_http.setOnClickListener {
            getCat()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_http
    override fun onRetryBtnClick() {
        TODO("Not yet implemented")
    }
}