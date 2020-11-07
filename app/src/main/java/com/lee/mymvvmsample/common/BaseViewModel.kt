package com.lee.mymvvmsample.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel : ViewModel() {
    /**
     * SupervisorJob 을 사용함.
     * 코루틴에서 익셉션이 발생해서 child Job 이 캔슬될때 parent Job 과 다른 child Job이 모두 캔슬되는 현상이 있는데
     * SupervisorJob을 사용하면 다른 child Job은 계속 동작을 시킬수 있다.
     */
    private val viewModelJob = SupervisorJob()
    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        /**
         * ViewModel 이 onCleared 될때 이 뷰모델에 있는 코루틴 스코프들을 캔슬시키기 위한 처리.
         * 이렇게 하면 뷰모델은 사라졌지만 혹시 남아있을지 모를 코루틴 메모리릭을 방지할 수 있다.
         */
        viewModelJob.cancel()
    }
}