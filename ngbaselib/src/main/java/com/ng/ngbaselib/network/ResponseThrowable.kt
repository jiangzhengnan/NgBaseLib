package com.ng.ngbaselib.network


class ResponseThrowable : Exception {
    var code: Int
    var errMsg: String

    constructor(error: ERROR, e: Throwable? = null) : super(e) {
        code = error.getKey()
        errMsg = error.getValue()
    }

    constructor(code: Int, msg: String, e: Throwable? = null) : super(e) {
        this.code = code
        this.errMsg = msg
    }

    constructor(base: IBaseResponse<*>, e: Throwable? = null) : super(e) {
        this.code = base.code()
        this.errMsg = base.msg().toString()
    }
}

