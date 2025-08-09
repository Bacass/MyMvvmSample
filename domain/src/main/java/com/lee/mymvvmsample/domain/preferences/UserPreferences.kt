package com.lee.mymvvmsample.domain.preferences

interface UserPreferences {
    var userEmail: String
    var userPass: String

    var cardRegDate: Long
    var isShowNotification: Boolean

    var communityId: Int

    var lastRequestApiTime: Long
    var lastStatus: Int
}


