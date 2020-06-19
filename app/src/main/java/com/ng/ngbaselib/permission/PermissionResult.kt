package com.ng.ngbaselib.permission

/**
 * 只能通过密封类内部的子类实例化对象，这时就可以执行里面的方法了
 * 密封类
 */
sealed class PermissionResult {
    object Grant : PermissionResult()
    class Deny(val permissions: Array<String>) : PermissionResult() //拒绝列表
    class Rationale(val permissions: Array<String>) : PermissionResult()//同意列表
}