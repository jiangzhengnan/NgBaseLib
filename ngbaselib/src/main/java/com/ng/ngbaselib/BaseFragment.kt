package com.ng.ngbaselib

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.ColorUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.alibaba.android.arouter.launcher.ARouter
import com.ng.ngbaselib.event.Message
import com.ng.ngbaselib.fragment.FragmentUserVisibleController
import com.ng.ngbaselib.utils.ColorUtil
import com.ng.ngbaselib.utils.ToastUtils
import com.ng.ngbaselib.view.StateLayout
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.lang.reflect.ParameterizedType

/**
 * base:
 * 各种状态展示layout
 * 各种状态展示dialog
 * 网络状态监听判断 todo
 *
 * 方法：
 * setStatusColor 设置状态栏颜色
 *
 * @author Jzn
 * @date 2020-06-12
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment(),
    FragmentUserVisibleController.UserVisibleCallback {
    var mTitle: String? = null

    //是否需要Loading布局
    private fun isNeedLoad(): Boolean {
        return true
    }

    protected lateinit var viewModel: VM
    protected var mBinding: DB? = null

    //是否第一次加载
    private var isFirst: Boolean = true
    private var dialog: MaterialDialog? = null

    //各种状态展示layout
    private var mLayoutId = 0
    private var mStateLayout: StateLayout? = null
    private var mContentFrameLayout: FrameLayout? = null
    private var mContentView: View? = null
    private var mParent: View? = null


    private var mUserVisibleController: FragmentUserVisibleController? = null

    init {
        mUserVisibleController = FragmentUserVisibleController(this, this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        ARouter.getInstance().inject(this)
        //绑定databind
        val cls =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<*>
        if (ViewDataBinding::class.java != cls && ViewDataBinding::class.java.isAssignableFrom(cls)) {
            mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        }
        //绑定多状态layout
        if (mContentView == null) {
            mContentView = createRootView(inflater, container, savedInstanceState)
        } else {
            val parent = mContentView?.parent
            if (parent != null && parent is ViewGroup) {
                parent.removeView(mContentView)
            }
        }


        initObserve()


        return mContentView
    }

    private fun initObserve() {
        EventBus.getDefault().register(this)
    }

    @Subscribe
    open fun onGetMessage(t: Message) {
    }

    open fun createRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        createViewModel()
        lifecycle.addObserver(viewModel)


        //注册 UI事件
        registorDefUIChange()
        initViewsAndEvents(mContentView, savedInstanceState)
        initListener()
        initData()

    }


    /**
     * 是否和 Activity 共享 ViewModel,默认不共享
     * Fragment 要和宿主 Activity 的泛型是同一个 ViewModel
     */
    open fun isShareVM(): Boolean = false


    /**
     * 反射 创建 ViewModel
     */
    @Suppress("UNCHECKED_CAST")
    private fun createViewModel() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val tp = type.actualTypeArguments[0]
            val tClass = tp as? Class<VM> ?: BaseViewModel::class.java
            val viewModelStore = if (isShareVM()) activity!!.viewModelStore else this.viewModelStore
            viewModel = ViewModelProvider(viewModelStore, ViewModelFactory()).get(tClass) as VM
        }
    }

    override fun onResume() {
        super.onResume()
        mUserVisibleController!!.resume()
    }

    override fun onPause() {
        super.onPause()
        mUserVisibleController!!.pause()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mUserVisibleController!!.activityCreated()
    }

    abstract fun getLayoutId(): Int

    abstract fun initListener()

    abstract fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?)

    abstract fun onRetryBtnClick()

    abstract fun initData()


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
            requireActivity().window.statusBarColor =
                if (color == 0) ColorUtil.getColor(activity!!, R.color.colorPrimaryDark) else color
        }
        if (ColorUtils.calculateLuminance(Color.TRANSPARENT) >= 0.5) {
            // 设置状态栏中字体的颜色为黑色
            requireActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            // 跟随系统
            requireActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    open fun <T : View?> findViewById(id: Int): T? {
        return if (mParent == null) null else mParent?.findViewById<View>(id) as T
    }

    /**
     * 注册 UI 事件
     */
    private fun registorDefUIChange() {
        viewModel.defUI.showDialog.observe(viewLifecycleOwner, Observer {
            showLoading()
        })
        viewModel.defUI.showEmpty.observe(viewLifecycleOwner, Observer {
            showEmptyLayout()
        })
        viewModel.defUI.showError.observe(viewLifecycleOwner, Observer {
            showLoadingErrorLayout(getString(R.string.loading_fail))
        })
        viewModel.defUI.dismissDialog.observe(viewLifecycleOwner, Observer {
            dismissLoading()
        })
        viewModel.defUI.toastEvent.observe(viewLifecycleOwner, Observer {
            ToastUtils.showShort(context, it)
        })
        viewModel.defUI.msgEvent.observe(viewLifecycleOwner, Observer {
            handleEvent(it)
        })
    }

    open fun handleEvent(msg: Message) {}


    /**
     * 打开等待框
     */
    protected fun showLoading() {
        if (dialog == null) {
            dialog = context?.let {
                MaterialDialog(it)
                    .cancelable(true)
                    .cornerRadius(8f)
                    .customView(R.layout.custom_progress_dialog_view, noVerticalPadding = true)
                    .lifecycleOwner(this)
                    .maxWidth(R.dimen.dialog_width)
            }
        }
        dialog?.show()
    }

    /**
     * 关闭等待框
     */
    private fun dismissLoading() {
        dialog?.run { if (isShowing) dismiss() }
    }

    fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    open fun startTo(targetClass: Class<out Activity>) {
        val intent = Intent(activity, targetClass)
        startActivity(intent)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    /**
     * 监听界面是否展示给用户，实现懒加载
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        mUserVisibleController!!.setUserVisibleHint(isVisibleToUser)
    }

    override fun isWaitingShowToUser(): Boolean {
        return mUserVisibleController!!.isWaitingShowToUser
    }

    override fun setWaitingShowToUser(waitingShowToUser: Boolean) {
        mUserVisibleController!!.isWaitingShowToUser = waitingShowToUser
    }

    override fun isVisibleToUser(): Boolean {
        return mUserVisibleController!!.isVisibleToUser
    }


    override fun callSuperSetUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }


    private var isFirstVisible = true

    private var isFirstInvisible = true

    private var isRealVisible = false

    override fun onVisibleToUserChanged(
        isVisibleToUser: Boolean,
        invokeInResumeOrPause: Boolean
    ) {
        if (isVisibleToUser) {
            if (!isRealVisible) {
                if (isFirstVisible) {
                    isFirstVisible = false
                    onUserFirstVisible()
                } else {
                    onUserVisible()
                }
                isRealVisible = true
            }
        } else {
            if (isRealVisible) {
                if (isFirstInvisible) {
                    isFirstInvisible = false
                    onUserFirstInvisible()
                } else {
                    onUserInvisible()
                }
                isRealVisible = false
            }
        }
    }

    /**
     * 用户可见，第一次可见不调用
     */
    open fun onUserVisible() {}

    /**
     * 用户不可见，第一次不可见不调用
     */
    open fun onUserInvisible() {}

    /**
     * 用户第一次可见
     */
    open fun onUserFirstVisible() {
    }

    /**
     * 用户第一次不可见
     */
    open fun onUserFirstInvisible() {}


    fun setText(tv: TextView, str: String?) {
        if (!str.isNullOrEmpty()) {
            tv.text = str
        } else {
            tv.text = "--"
        }
    }

}