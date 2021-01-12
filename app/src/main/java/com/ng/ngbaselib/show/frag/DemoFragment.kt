package com.ng.ngbaselib.show.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.ng.ngbaselib.BaseFragment
import com.ng.ngbaselib.R
import com.ng.ngbaselib.show.viewmodel.HomeViewModel

/**
 * 描述:
 * @author Jzn
 * @date 2020-06-12
 */
class DemoFragment : BaseFragment<HomeViewModel, ViewBinding>() {


    override fun getLayoutId(): Int = R.layout.fragment_demo

    override fun onRetryBtnClick() {
    }

    override fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?) {

    }


    override fun initListener() {
    }

    override fun initData() {
    }

    override fun createViewBinding(inflater: LayoutInflater): ViewBinding? = null

    override fun createViewModel(): HomeViewModel? = null
}