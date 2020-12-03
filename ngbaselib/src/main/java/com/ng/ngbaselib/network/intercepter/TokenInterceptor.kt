package com.ng.ngbaselib.network.intercepter

import android.os.UserManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 描述:
 * @author Jzn
 * @date 2020/7/6
 */
class TokenInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val headers: HashMap<String, String> = HashMap()
        headers["Accept-Encoding"] = "identity"
        headers["Accept"] = "application/vnd.github.mercy-preview+json"

        // headers["okises-jwt"] = UserManager.getInstance().getToken()
        return chain.proceed(chain.request().newBuilder().run {
            if (!headers.isNullOrEmpty()) {
                for (headMap in headers) {
                    addHeader(headMap.key, headMap.value).build()
                }
            }
            build()
        })
    }
}