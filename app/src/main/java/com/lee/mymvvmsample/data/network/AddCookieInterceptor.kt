package com.lee.mymvvmsample.data.network

import com.lee.mymvvmsample.common.utils.AppPrefs
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AddCookieInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Cookie", AppPrefs.appCookie)
        return chain.proceed(builder.build())
    }
}
