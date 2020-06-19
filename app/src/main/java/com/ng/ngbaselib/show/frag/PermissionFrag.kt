package com.ng.ngbaselib.show.frag

import android.Manifest
import android.content.DialogInterface
import android.view.View
import androidx.lifecycle.Observer
import com.ng.ngbaselib.R
import com.ng.ngbaselib.permission.PermissionResult
import com.ng.ngbaselib.permission.PermissionUtil
import com.ng.ngbaselib.permission.Permissions
import kotlinx.android.synthetic.main.fragment_permission.*

/**
 * 描述:
 * @author Jzn
 * @date 2020/6/19
 */
class PermissionFrag : BaseFragment() {

    //需要申请的权限列表
    private val mPermissions = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    )

    override fun initViewsAndEvents(v: View) {
        btn_1_permission.setOnClickListener {
            getPermission()
        }
    }

    private fun getPermission() {


        Permissions(this).requestArray(mPermissions).observe(
                this, Observer {
            when (it) {
                is PermissionResult.Grant -> {
                    startIntent()
                }
                // 进入设置界面申请权限
                is PermissionResult.Rationale -> {


                    PermissionUtil.showDialog(activity, "考虑一下申请权限",
                            "该权限是用来干嘛的，没有它会巴拉巴拉，点击确定进入权限设置界面进行更高",
                            DialogInterface.OnClickListener { dialog, _ ->
                                dialog.dismiss()
                                activity?.finish()
                            }
                    )


//                    AppSettingsDialog.Builder(this)
//                            .setTitle("考虑一下申请权限")
//                            .setRationale("该权限是用来干嘛的，没有它会巴拉巴拉，点击确定进入权限设置界面进行更高")
//                            .build()
//                            .show()
                    //can finish
                }
                // 进入设置界面申请权限
                is PermissionResult.Deny -> {
//                    AppSettingsDialog.Builder(this)
//                            .setTitle("申请权限")
//                            .setRationale("没有相关权限应用将无法正常运行，点击确定进入权限设置界面来进行更改")
//                            .build()
//                            .show()
                    //can finish
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
}