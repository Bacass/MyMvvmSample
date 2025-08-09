package com.lee.mymvvmsample.data.local

import com.chibatching.kotpref.KotprefModel

object CookiePrefs : KotprefModel() {
    var appCookie: String by stringPref()
}


