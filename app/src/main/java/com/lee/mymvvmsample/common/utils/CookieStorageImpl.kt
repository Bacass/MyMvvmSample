package com.lee.mymvvmsample.common.utils

import com.lee.mymvvmsample.data.local.CookieStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CookieStorageImpl @Inject constructor() : CookieStorage {
    override var cookie: String
        get() = AppPrefs.appCookie
        set(value) {
            AppPrefs.appCookie = value
        }
}


