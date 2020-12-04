package com.ng.ngbaselib.show.frag.http

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.ng.ngbaselib.BaseFragment
import com.ng.ngbaselib.R
import com.ng.ngbaselib.expand.UIExpand.click
import com.ng.ngbaselib.expand.UIExpand.screenWidth
import com.ng.ngbaselib.show.MainActivity
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

        btn_get_http.click {
            //get 请求
            requestWithGet()
        }
        btn_flow_http.click {
            //流 请求
            requestWithFlow()
        }

    }

    private fun requestWithFlow() {
        showToast("Flow 请求 ")
        mSearchWord = et_search.text.toString()
        viewModel.getSearchResultWithFlow(mSearchWord)
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


    //Context的扩展
    //使用内联函数的泛型参数 reified 特性来实现
    inline fun <reified T : Activity> Context.startActivity() {
        val intent = Intent(this, T::class.java)
        if (this !is Activity) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }
}

