package com.lee.mymvvmsample.domain.model

sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    inline fun <T> fold(onLeft: (L) -> T, onRight: (R) -> T): T =
        when (this) {
            is Left -> onLeft(value)
            is Right -> onRight(value)
        }
}


