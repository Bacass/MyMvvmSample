package com.lee.mymvvmsample.data.network

import com.lee.mymvvmsample.data.local.CookieStorage
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AddCookieInterceptor @Inject constructor(
    private val cookieStorage: CookieStorage,
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Cookie", cookieStorage.cookie)
        return chain.proceed(builder.build())
    }
}


