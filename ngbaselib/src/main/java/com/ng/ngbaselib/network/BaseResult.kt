package com.ng.ngbaselib.network

import com.ng.ngbaselib.network.IBaseResponse
import java.io.Serializable


data class BaseResult<T>(
    var code: Int,
    var msg: String?,
    var data: T?,
    var suc: Boolean
) : IBaseResponse<T>,Serializable {
    override fun code() = code

    override fun msg() = msg

    override fun data() = data

    override fun suc() = suc

}