package com.lee.mymvvmsample.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel : ViewModel() {
    private val viewModelJob = SupervisorJob()
    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}