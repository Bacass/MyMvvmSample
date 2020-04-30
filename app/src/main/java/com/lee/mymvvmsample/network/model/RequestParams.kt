package com.lee.mymvvmsample.network.model

class RequestImageParam {
    var key: String? = null
    var q: String? = null
    var image_type: String? = null
    var page: Int = 1
    var per_page: Int = 20
}

class RequestVideoParam {
    var key: String? = null
    var q: String? = null
    var page: Int = 1
    var per_page: Int = 20
}