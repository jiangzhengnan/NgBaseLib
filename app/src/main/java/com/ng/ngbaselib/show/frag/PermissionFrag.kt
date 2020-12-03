package com.ng.ngbaselib.show.frag

import android.Manifest
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.ng.ngbaselib.BaseFragment
import com.ng.ngbaselib.R
import com.ng.ngbaselib.permission.PermissionResult
import com.ng.ngbaselib.permission.PermissionUtil
import com.ng.ngbaselib.permission.Permissions
import com.ng.ngbaselib.show.viewmodel.HomeViewModel
import com.ng.ngbaselib.utils.MLog
import kotlinx.android.synthetic.main.fragment_permission.*

/**
 * 描述:  权限申请部分
 * @author Jzn
 * @date 2020/6/19
 */
class PermissionFrag : BaseFragment<HomeViewModel, ViewDataBinding>() {

    //需要申请的权限列表
    private val mPermissions = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    )

    private val mPermissionStr = Manifest.permission.CAMERA


    private fun getPermission() {
        Permissions(this).request(mPermissionStr).observe(
                this, Observer {
            when (it) {
                is PermissionResult.Grant -> {
                    MLog.d("PermissionResult.Grant")
                    startIntent()
                }
                // 进入设置界面申请权限
                is PermissionResult.Rationale -> {
                    MLog.d("PermissionResult.Rationale")
                    PermissionUtil.showDialog(activity, "考虑一下申请权限",
                            "该权限是用来干嘛的，没有它会巴拉巴拉，点击确定进入权限设置界面进行更高",
                            DialogInterface.OnClickListener { dialog, _ ->
                                dialog.dismiss()
                                activity?.finish()
                            }
                    )
                }
                // 进入设置界面申请权限
                is PermissionResult.Deny -> {
                    MLog.d("PermissionResult.Deny")
                    PermissionUtil.showDialog(activity, "申请权限",
                            "没有相关权限应用将无法正常运行，点击确定进入权限设置界面来进行更改",
                            DialogInterface.OnClickListener { dialog, _ ->
                                dialog.dismiss()
                                activity?.finish()
                            }
                    )
                }
            }
        }
        )

    }

    private fun getPermissions(permissions: Array<String>) {
        Permissions(this).requestArray(permissions).observe(
                this, Observer {
            when (it) {
                is PermissionResult.Grant -> {
                    MLog.d("PermissionResult.Grant")
                    startIntent()
                }
                // 进入设置界面申请权限
                is PermissionResult.Rationale -> {
                    MLog.d("PermissionResult.Rationale")
                    PermissionUtil.showDialog(activity, "考虑一下申请权限",
                            "该权限是用来干嘛的，没有它会巴拉巴拉，点击确定进入权限设置界面进行更高",
                            DialogInterface.OnClickListener { dialog, _ ->
                                dialog.dismiss()
                                activity?.finish()
                            }
                    )
                }
                // 进入设置界面申请权限
                is PermissionResult.Deny -> {
                    MLog.d("PermissionResult.Deny")
                    PermissionUtil.showDialog(activity, "申请权限",
                            "没有相关权限应用将无法正常运行，点击确定进入权限设置界面来进行更改",
                            DialogInterface.OnClickListener { dialog, _ ->
                                dialog.dismiss()
                                activity?.finish()
                            }
                    )
                }
            }
        }
        )
    }

    private fun startIntent() {
        //startActivity<MainActivity>(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_permission
    override fun onRetryBtnClick() {
    }

    override fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?) {
        btn_1_permission.setOnClickListener {
            getPermissions(mPermissions)
        }
        btn_2_permission.setOnClickListener {
            getPermission()
        }
    }

    override fun initListener() {
    }

    override fun initData() {
    }
}