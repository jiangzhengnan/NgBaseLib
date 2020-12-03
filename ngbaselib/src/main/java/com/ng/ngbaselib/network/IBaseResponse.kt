package com.ng.ngbaselib.network

interface IBaseResponse<T> {
    fun code(): Int
    fun msg(): String?
    fun data(): T?
    fun suc(): Boolean
}