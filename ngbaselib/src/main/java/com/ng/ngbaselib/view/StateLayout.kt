package com.ng.ngbaselib.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.airbnb.lottie.LottieAnimationView
import com.ng.ngbaselib.R
import com.ng.ngbaselib.utils.ViewUtils

/**
 * 描述:各种状态View
 * @author Jzn
 * @date 2020/6/20
 */
class StateLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    /**
     * 空数据View
     */
    private var mEmptyView = 0

    /**
     * 状态View
     */
    private var mErrorView = 0

    /**
     * 加载View
     */
    private var mLoadingView = 0
    private lateinit var animationView: LottieAnimationView

    init {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.LoadingLayout, 0, 0)
        try {
            mErrorView = a.getResourceId(R.styleable.LoadingLayout_stateView, R.layout.layout_center_load_error)
            mLoadingView = a.getResourceId(R.styleable.LoadingLayout_loadingView, R.layout.layout_center_loading)
            mEmptyView = a.getResourceId(R.styleable.LoadingLayout_emptyView, R.layout.layout_center_empty)
            val inflater = LayoutInflater.from(getContext())
            inflater.inflate(mErrorView, this, true)
            inflater.inflate(mLoadingView, this, true)
            inflater.inflate(mEmptyView, this, true)
        } finally {
            a.recycle()
        }
    }

    /**
     * 布局加载完成后隐藏所有View
     */
    override fun onFinishInflate() {
        super.onFinishInflate()
        for (i in 0 until childCount - 1) {
            getChildAt(i).visibility = View.GONE
            if (i == 1) {
                val animationView: LottieAnimationView = getChildAt(i).findViewById(R.id.animation_view)
                animationView.clearAnimation()
            }
        }
    }

    /**
     * 设置重新加载点击事件
     * R.id.state_retry 这个id在图表的Loading中是给的LinearLayout
     *
     * @param listener
     */
    fun setRetryClickListener(listener: OnClickListener?) {
        if (listener != null) findViewById<View>(R.id.state_retry).setOnClickListener(listener)
    }

    /**
     * State View
     */
    fun showError() {
        for (i in 0 until this.childCount) {
            val child = getChildAt(i)
            if (i == 0) {
                child.visibility = View.VISIBLE
            } else if (i == 1) {
                hideAnimationView(child)
            } else {
                child.visibility = View.GONE
            }
        }
        visibility = View.VISIBLE
    }

    /**
     * Empty view
     */
    fun showEmpty() {
        for (i in 0 until this.childCount) {
            val child = getChildAt(i)
            if (i == 2) {
                child.visibility = View.VISIBLE
            } else if (i == 1) {
                hideAnimationView(child)
            } else {
                child.visibility = View.GONE
            }
        }
        visibility = View.VISIBLE
    }

    /**
     * Loading view
     */
    fun showLoading() {
        for (i in 0 until this.childCount) {
            val child = getChildAt(i)
            if (i == 1) {
                child.visibility = View.VISIBLE
                animationView = child.findViewById(R.id.animation_view)
                val animBackUp = findViewById<View>(R.id.animation_backup)
                try {
                    if (animBackUp != null) animBackUp.visibility = View.GONE
                    animationView.visibility = View.VISIBLE
                    //animationView.setAnimation("anim.json")
                    animationView.playAnimation()
                } catch (e: Exception) {
                    if (animBackUp != null) animBackUp.visibility = View.VISIBLE
                    animationView.setVisibility(View.GONE)
                    e.printStackTrace()
                }
            } else {
                child.visibility = View.GONE
            }
        }
        visibility = View.VISIBLE
    }


    /**
     * @param text
     */
    fun showLoading(text: String?) {
        for (i in 0 until this.childCount) {
            val child = getChildAt(i)
            if (i == 1) {
                child.visibility = View.VISIBLE
                (findViewById<View>(R.id.loading_text) as TextView).text = text + ""
                animationView = child.findViewById(R.id.animation_view)
                val animBackUp = findViewById<View>(R.id.animation_backup)
                try {
                    if (animBackUp != null) animBackUp.visibility = View.GONE
                    animationView.setVisibility(View.VISIBLE)
                    //animationView.setAnimation("anim.json")
                    animationView.playAnimation()
                } catch (e: Exception) {
                    if (animBackUp != null) animBackUp.visibility = View.VISIBLE
                    animationView.setVisibility(View.GONE)
                    e.printStackTrace()
                }
            } else {
                child.visibility = View.GONE
            }
        }
        visibility = View.VISIBLE
    }

    /**
     * Empty view
     *
     * @param text
     */
    fun showEmpty(text: String) {
        for (i in 0 until this.childCount) {
            val child = getChildAt(i)
            if (i == 2) {
                child.visibility = View.VISIBLE
                (child.findViewById<View>(R.id.empty_text) as TextView).text = text + ""
            } else if (i == 1) {
                hideAnimationView(child)
            } else {
                child.visibility = View.GONE
            }
        }
        visibility = View.VISIBLE
    }

    fun showEmpty(@DrawableRes resId: Int, text: String) {
        for (i in 0 until this.childCount) {
            val child = getChildAt(i)
            if (i == 2) {
                child.visibility = View.VISIBLE
                //                ((ImageView) child.findViewById(R.id.load_state_icon)).setImageResource(resId);
                (child.findViewById<View>(R.id.empty_text) as TextView).text = text + ""
            } else if (i == 1) {
                hideAnimationView(child)
            } else {
                child.visibility = View.GONE
            }
        }
        visibility = View.VISIBLE
    }

    fun showEmpty(textSize: Int, text: String, margin: Int) {
        for (i in 0 until this.childCount) {
            val child = getChildAt(i)
            if (i == 2) {
                child.visibility = View.VISIBLE
                (child.findViewById<View>(R.id.empty_text) as TextView).text = text + ""
                val layoutParams = child.findViewById<View>(R.id.empty_text).layoutParams as MarginLayoutParams
                layoutParams.topMargin = margin
            } else if (i == 1) {
                hideAnimationView(child)
            } else {
                child.visibility = View.GONE
            }
        }
        visibility = View.VISIBLE
    }

    /**
     * @param tips
     */
    fun showError(tips: String?) {
        for (i in 0 until this.childCount) {
            val child = getChildAt(i)
            if (i == 0) {
                child.visibility = View.VISIBLE
                (child.findViewById<View>(R.id.load_state_tv) as TextView).text = tips + ""
            } else if (i == 1) {
                hideAnimationView(child)
            } else {
                child.visibility = View.GONE
            }
        }
        visibility = View.VISIBLE
    }

    /**
     * @param stateImageViewId
     * @param tips
     */
    fun showError(stateImageViewId: Int, tips: String) {
        for (i in 0 until this.childCount) {
            val child = getChildAt(i)
            if (i == 0) {
                child.visibility = View.VISIBLE
                //                ((ImageView) child.findViewById(R.id.load_state_img)).setImageResource(stateImageViewId);
                (child.findViewById<View>(R.id.load_state_tv) as TextView).text = tips + ""
            } else if (i == 1) {
                hideAnimationView(child)
            } else {
                child.visibility = View.GONE
            }
        }
        visibility = View.VISIBLE
    }

    /**
     * @param stateImageViewId
     */
    fun showError(stateImageViewId: Int) {
        for (i in 0 until this.childCount) {
            val child = getChildAt(i)
            if (i == 0) {
                child.visibility = View.VISIBLE
                //                ((ImageView) child.findViewById(R.id.load_state_img)).setImageResource(stateImageViewId);
            } else if (i == 1) {
                hideAnimationView(child)
            } else {
                child.visibility = View.GONE
            }
        }
        visibility = View.VISIBLE
    }

    private fun hideAnimationView(child: View) {
        val animationView: LottieAnimationView = child.findViewById(R.id.animation_view)
        animationView.pauseAnimation()
        child.visibility = View.GONE
    }

    fun hide() {
        visibility = View.GONE
        for (i in 0 until this.childCount) {
            val child = getChildAt(i)
            if (i == 1) {
                hideAnimationView(child)
            }
            child.visibility = View.GONE
        }
    }

    fun setEmptyViewText(text: String?) {
        (findViewById<View>(R.id.empty_text) as TextView).text = text
    }

    fun pauseAnimation() {
        if (animationView != null) {
            animationView.pauseAnimation()
        }
    }

    fun setEmptyCenterInParent(margin: Int) {
        try {
            val child = getChildAt(2)
            val stateImage = child.findViewById<View>(R.id.load_state_icon)
            val parm = stateImage.layoutParams as LinearLayout.LayoutParams
            parm.setMargins(0, -ViewUtils.dpToPx(margin.toFloat()), 0, 0)
            stateImage.layoutParams = parm
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            val child = getChildAt(0)
            val stateImage = child.findViewById<View>(R.id.load_state_icon)
            val parm = stateImage.layoutParams as LinearLayout.LayoutParams
            parm.setMargins(0, -ViewUtils.dpToPx(margin.toFloat()), 0, 0)
            stateImage.layoutParams = parm
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setEmptyCenterInParent() {
        setEmptyCenterInParent(50)
    }

    fun clearBackground() {
        background = null
        val size = childCount
        for (i in 0 until size) {
            val view = getChildAt(i)
            if (view != null) {
                view.background = null
            }
        }
    }
}