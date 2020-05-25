package com.lee.mymvvmsample.common.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * 똑같은 신호가 두번 전달되는 상태 방지
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {

    /**
     * LiveData는 View의 재활성화(start나 resume 상태로 재진입)가 되면서 LiveData가 observe를 호출하여,
     * 불필요한 Observer Event까지 일어나는 경우가 있다.
     *
     * 신호가 두번 전달되는 현상을 막기위해 data를 value 에 넣고 observer.onChanged() 를 호출시킨후 null 시킨다.
     * data가 있는 경우에만 onChanged()를 호출시킨다.
     */
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