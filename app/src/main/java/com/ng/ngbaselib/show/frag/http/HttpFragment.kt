package com.ng.ngbaselib.show.frag.http

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ng.ngbaselib.BaseFragment
import com.ng.ngbaselib.R
import com.ng.ngbaselib.ViewModelFactory
import com.ng.ngbaselib.databinding.FragmentHttpBinding
import com.ng.ngbaselib.expand.UIExpand.click
import com.ng.ngbaselib.http.bean.SearchResult
import com.ng.ngbaselib.utils.MLog

/**
 * 描述:
 * @author Jzn
 * @date 2020/6/20
 */
class HttpFragment : BaseFragment<SearchViewModel, FragmentHttpBinding>() {

    override fun createViewBinding(inflater: LayoutInflater): FragmentHttpBinding? =
        FragmentHttpBinding.inflate(inflater)

    override fun createViewModel(): SearchViewModel {
        return ViewModelProvider(
            this,
            ViewModelFactory()
        ).get(SearchViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.fragment_http

    override fun onRetryBtnClick() {
    }

    private var mSearchWord: String = ""


    override fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?) {

        mBinding!!.btnGetHttp.click {
            //get 请求
            requestWithGet()
        }
        mBinding!!.btnFlowHttp.click {
            //流 请求
            requestWithFlow()
        }

    }

    private fun requestWithFlow() {
        showToast("Flow 请求 ")
        mSearchWord = mBinding!!.etSearch.text.toString()
        mViewModel!!.getSearchResultTestFlow(mSearchWord)
    }

    private fun requestWithGet() {
        showToast("Get 请求 ")
        mSearchWord = mBinding!!.etSearch.text.toString()
        mViewModel!!.getSearchResult(mSearchWord)

    }

    override fun initListener() {
    }

    override fun initData() {
        mViewModel!!.searchResult.observe(this, Observer {
            MLog.d("搜索结果:${it}")
            //tv_result_http.text = it.toString()
            addResultView(it)
        })
    }

    private fun addResultView(it: SearchResult?) {
        val tvTempView = TextView(context)
        tvTempView.isSingleLine = true
        tvTempView.text = it.toString()
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        tvTempView.gravity = Gravity.CENTER
        lp.setMargins(5, 5, 5, 5)
        mBinding!!.llTvResultHttp.addView(tvTempView, lp)
    }


}

