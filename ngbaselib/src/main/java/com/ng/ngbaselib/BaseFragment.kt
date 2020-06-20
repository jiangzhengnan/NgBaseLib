package com.ng.ngbaselib

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import com.ng.ngbaselib.utils.ColorUtil
import com.ng.ngbaselib.view.StateLayout

/**
 * base:
 * 各种状态展示view
 * 各种状态展示dialog
 * 网络状态监听判断
 *
 * 方法：
 * setStatusColor 设置状态栏颜色
 *
 * @author Jzn
 * @date 2020-06-12
 */
abstract class BaseFragment : Fragment() {

    //是否需要Loading布局
    private fun isNeedLoad(): Boolean {
        return true
    }

    private var mLayoutId = 0
    private var mStateLayout: StateLayout? = null
    private var mContentFrameLayout: FrameLayout? = null
    private var mContentView: View? = null
    private var mParent: View? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mContentView == null) {
            mContentView = createRootView(inflater, container, savedInstanceState)
        } else {
            val parent = mContentView?.parent
            if (parent != null && parent is ViewGroup) {
                parent.removeView(mContentView)
            }
        }
        return mContentView
    }

    open fun createRootView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (isNeedLoad()) {
            mLayoutId = getLayoutId()
            if (mLayoutId == 0) {
                throw NullPointerException("布局为空")
            }
            setRoot(inflater.inflate(R.layout.fragment_basic, container, false))
            mStateLayout = findViewById<View>(R.id.loading_layout) as StateLayout
            mContentFrameLayout = findViewById<View>(R.id.content_layout) as FrameLayout
            inflater.inflate(mLayoutId, mContentFrameLayout, true) // 加载fragment布局
        } else {
            mLayoutId = getLayoutId()
            setRoot(inflater.inflate(mLayoutId, container, false))
        }
        return getRoot()
    }

    protected open fun getRoot(): View? {
        return this.mParent
    }

    protected open fun setRoot(view: View) {
        this.mParent = view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewsAndEvents(mContentView)
    }

    abstract fun initViewsAndEvents(v: View?)

    abstract fun getLayoutId(): Int

    abstract fun onRetryBtnClick()

    open fun showLoadingLayout(msg: String?) {
        if (mContentFrameLayout != null) {
            mContentFrameLayout?.visibility = View.GONE
        }
        if (mStateLayout != null) {
            mStateLayout?.showLoading(msg)
        }
    }

    open fun showEmptyLayout() {
        if (mContentFrameLayout != null) {
            mContentFrameLayout?.visibility = View.GONE
        }
        if (mStateLayout != null) {
            mStateLayout?.showEmpty()
        }
    }

    open fun showLoadingErrorLayout(msg: String?) {
        if (mContentFrameLayout != null) {
            mContentFrameLayout?.visibility = View.GONE
        }
        if (mStateLayout != null) {
            mStateLayout?.showError(msg)
            mStateLayout?.setRetryClickListener(View.OnClickListener { onRetryBtnClick() })
        }
    }


    open fun showContentLayout() {
        if (mContentFrameLayout != null) {
            mContentFrameLayout?.visibility = View.VISIBLE
        }
        if (mStateLayout != null) {
            mStateLayout?.visibility = View.GONE
            mStateLayout?.pauseAnimation()
        }
    }

    /**
     * 设置状态栏颜色
     */
    @SuppressLint("ObsoleteSdkInt")
    private fun setStatusColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            requireActivity().window.statusBarColor = if (color == 0) ColorUtil.getColor(activity!!, R.color.colorPrimaryDark) else color
        }
        if (ColorUtils.calculateLuminance(Color.TRANSPARENT) >= 0.5) {
            // 设置状态栏中字体的颜色为黑色
            requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            // 跟随系统
            requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    open fun <T : View?> findViewById(id: Int): T? {
        return if (mParent == null) null else mParent?.findViewById<View>(id) as T
    }
}