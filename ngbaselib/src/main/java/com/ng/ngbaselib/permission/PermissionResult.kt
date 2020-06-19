package com.ng.ngbaselib.permission

/**
 * 只能通过密封类内部的子类实例化对象，这时就可以执行里面的方法了
 * 密封类
 */
sealed class PermissionResult {
    object Grant : PermissionResult()
    class Deny(val permissions: Array<String>) : PermissionResult() //拒绝列表

    /**
     * 1.都没有请求过这个权限，用户不一定会拒绝你，所以你不用解释，故返回false;
     * 2.请求了但是被拒绝了，此时返回true，意思是你该向用户好好解释下了；
     * 3.请求权限被禁止了，也不给你弹窗提醒了，所以你也不用解释了，故返回fasle;
     * 4.请求被允许了，都给你权限了，还解释个啥，故返回false。
     */
    class Rationale(val permissions: Array<String>) : PermissionResult()//应不应该解释下请求这个权限的目的列表
}