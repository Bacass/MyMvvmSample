package com.lee.mymvvmsample.common.utils

import com.chibatching.kotpref.KotprefModel

object AppPrefs : KotprefModel() {
    var appCookie: String by stringPref()

    var userEmail: String by stringPref()
    var userPass: String by stringPref()

    var cardRegDate: Long by longPref()
    var isShowNotification: Boolean by booleanPref(true)

    var communityId: Int by intPref(1)

    var lastRequestApiTime: Long by longPref()

    var lastStatus: Int by intPref(0)
}
