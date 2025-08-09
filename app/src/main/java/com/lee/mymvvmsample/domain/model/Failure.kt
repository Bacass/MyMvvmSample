package com.lee.mymvvmsample.domain.model

sealed class Failure {
    object Network : Failure()
    data class Server(val code: Int, val message: String?) : Failure()
    object NoData : Failure()
    data class InvalidInput(val message: String) : Failure()
    data class Unknown(val message: String?) : Failure()
}


