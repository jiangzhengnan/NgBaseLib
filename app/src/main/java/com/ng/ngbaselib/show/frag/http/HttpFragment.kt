package com.ng.ngbaselib.show.frag.http

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.ng.ngbaselib.BaseFragment
import com.ng.ngbaselib.R
import com.ng.ngbaselib.show.viewmodel.HomeViewModel
import com.ng.ngbaselib.utils.MLog
import kotlinx.android.synthetic.main.fragment_http.*

/**
 * 描述:
 * @author Jzn
 * @date 2020/6/20
 */
class HttpFragment : BaseFragment<SearchViewModel, ViewDataBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_http

    override fun onRetryBtnClick() {
    }

    private var mSearchWord : String = ""

    override fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?) {

        btn_get_http.setOnClickListener {
            //get 请求
            requestWithGet()
        }
        btn_post_http.setOnClickListener {
            //post 请求
            requestWithPost()
        }
    }

    private fun requestWithPost() {
        showToast("Post 请求 ")
        mSearchWord = et_search.text.toString()

    }

    private fun requestWithGet() {
        showToast("Get 请求 ")
        mSearchWord = et_search.text.toString()
        viewModel.getSearchResult(mSearchWord)

    }

    override fun initListener() {
    }

    override fun initData() {
        viewModel.searchResult.observe(this, Observer {
            MLog.d("搜索结果:${it}")
            tv_result_http.text = it.toString()

        })
    }
}