package com.lee.mymvvmsample.common.utils

import com.lee.mymvvmsample.domain.preferences.UserPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesImpl @Inject constructor() : UserPreferences {
    override var userEmail: String
        get() = AppPrefs.userEmail
        set(value) { AppPrefs.userEmail = value }

    override var userPass: String
        get() = AppPrefs.userPass
        set(value) { AppPrefs.userPass = value }

    override var cardRegDate: Long
        get() = AppPrefs.cardRegDate
        set(value) { AppPrefs.cardRegDate = value }

    override var isShowNotification: Boolean
        get() = AppPrefs.isShowNotification
        set(value) { AppPrefs.isShowNotification = value }

    override var communityId: Int
        get() = AppPrefs.communityId
        set(value) { AppPrefs.communityId = value }

    override var lastRequestApiTime: Long
        get() = AppPrefs.lastRequestApiTime
        set(value) { AppPrefs.lastRequestApiTime = value }

    override var lastStatus: Int
        get() = AppPrefs.lastStatus
        set(value) { AppPrefs.lastStatus = value }
}


