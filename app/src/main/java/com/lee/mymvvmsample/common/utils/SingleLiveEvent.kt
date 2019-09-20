package com.lee.mymvvmsample.common.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * 똑같은 신호가 두번 전달되는 상태 방지
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {
    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T?>) {
        if (hasObservers()) {
            throw Throwable()
        }

        super.observe(owner, Observer { data ->
            // We ignore any null values and early return
            if (data == null) return@Observer
            observer.onChanged(data)
            value = null
        })
    }

    @MainThread
    fun sendEvent(data: T) {
        value = data
    }
}