package com.ng.ngbaselib.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * 描述:
 * @author Jzn
 * @date 2020-06-12
 */
abstract class BaseFragment :Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(getLayoutId(), container, false)
        initViewsAndEvents(v)
        return v
    }

    abstract fun initViewsAndEvents(v: View)

    abstract fun getLayoutId(): Int
}