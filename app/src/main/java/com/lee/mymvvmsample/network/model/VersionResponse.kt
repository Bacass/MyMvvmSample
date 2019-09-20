package com.lee.mymvvmsample.network.model

data class VersionResponse(
    val platform : String,
    val version : String?,
    val status : Boolean
)