package com.lee.mymvvmsample.data.network

import com.lee.mymvvmsample.data.local.CookieStorage
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ReceivedCookieInterceptor @Inject constructor(
    private val cookieStorage: CookieStorage,
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            for (header in originalResponse.headers("Set-Cookie")) {
                if (header.isNotEmpty()) {
                    cookieStorage.cookie = header
                }
            }
        }
        return originalResponse
    }
}


