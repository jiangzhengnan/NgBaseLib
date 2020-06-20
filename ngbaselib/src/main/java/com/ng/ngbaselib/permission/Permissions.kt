package com.ng.ngbaselib.permission

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData

/**
 * 单例模式权限管理类，抽离权限申请的回调逻辑到fragment中
 */
class Permissions {

    @Volatile
    private var permissionFragment: PermissionFragment? = null

    private fun getInstance(fragmentManager: FragmentManager) =
            permissionFragment ?: synchronized(this) {
                permissionFragment
                        ?: if (fragmentManager.findFragmentByTag(TAG) == null) PermissionFragment().run {
                            fragmentManager.beginTransaction().add(this, TAG).commitNow()
                            this
                        } else fragmentManager.findFragmentByTag(TAG) as PermissionFragment
            }

    companion object {
        const val TAG = "permissions"
    }

    constructor(activity: AppCompatActivity) {
        permissionFragment = getInstance(activity.supportFragmentManager)
    }

    constructor(fragment: Fragment) {
        permissionFragment = getInstance(fragment.childFragmentManager)
    }

    fun request(vararg permissions: String): MutableLiveData<PermissionResult> {
        return this.requestArray(permissions)
    }

    fun requestArray(permissions: Array<out String>): MutableLiveData<PermissionResult> {
        permissionFragment!!.requestPermissions(permissions)
        return permissionFragment!!.liveData
    }
}