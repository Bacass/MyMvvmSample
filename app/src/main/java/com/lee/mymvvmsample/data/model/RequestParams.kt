package com.lee.mymvvmsample.data.model

data class RequestImageParam(
    var key: String? = null,
    var q: String? = null,
    var image_type: String? = null,
    var page: Int = 1,
    var per_page: Int = 20
)