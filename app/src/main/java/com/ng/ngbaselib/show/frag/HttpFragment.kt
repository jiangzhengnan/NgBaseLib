package com.ng.ngbaselib.show.frag

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.ng.ngbaselib.BaseFragment
import com.ng.ngbaselib.R
import com.ng.ngbaselib.show.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_http.*

/**
 * 描述:
 * @author Jzn
 * @date 2020/6/20
 */
class HttpFragment: BaseFragment<HomeViewModel, ViewDataBinding>() {
    //得到随机小猫猫图片，get请求
    private val HTTP_CAT = "https://api.thecatapi.com/v1/images/search"


    private fun getCat() {


    }


    override fun getLayoutId(): Int = R.layout.fragment_http
    override fun onRetryBtnClick() {
    }

    override fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?) {
        btn_1_http.setOnClickListener {
            getCat()
        }
    }

    override fun lazyLoadData() {
    }
}