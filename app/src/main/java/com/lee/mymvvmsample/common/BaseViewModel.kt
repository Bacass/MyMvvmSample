package com.lee.mymvvmsample.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

abstract class BaseViewModel : ViewModel() {
    val uiScope = viewModelScope

    @CallSuper
    override fun onCleared() {
        super.onCleared()
    }
}
