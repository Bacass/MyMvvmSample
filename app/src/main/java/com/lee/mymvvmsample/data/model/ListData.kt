package com.lee.mymvvmsample.data.model

data class ListData<T>(var items: List<out T> = listOf(), val rowsCount: Int = 0)
