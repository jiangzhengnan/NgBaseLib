package com.ng.ngbaselib.show.frag

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.ng.ngbaselib.BaseFragment
import com.ng.ngbaselib.R

/**
 * 描述:
 * @author Jzn
 * @date 2020-06-12
 */
class DemoFragment : BaseFragment<HomeViewModel, ViewDataBinding>() {


    override fun getLayoutId(): Int = R.layout.fragment_demo

    override fun onRetryBtnClick() {
    }

    override fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?) {

    }

    override fun lazyLoadData() {
    }
}