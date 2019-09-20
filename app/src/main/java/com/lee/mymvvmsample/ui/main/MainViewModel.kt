package com.lee.mymvvmsample.ui.main

import com.lee.mymvvmsample.BuildConfig
import com.lee.mymvvmsample.network.SampleRepository
import com.lee.mymvvmsample.network.SampleResult
import com.lee.mymvvmsample.common.BaseViewModel
import com.lee.mymvvmsample.common.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class MainViewModel(private val repository: SampleRepository): BaseViewModel() {
    sealed class LoginResult {
        object Success : LoginResult()
        class Fail(val errorMsg: String) : LoginResult()
        object NetworkError : LoginResult()
        class ServerError(val errorMsg: String) : LoginResult()
        class Update(val newVersion: String) : LoginResult()
    }

    val loginResultEvent = SingleLiveEvent<LoginResult>()

    fun processVersion() {
        uiScope.launch {
            repository.serviceVersion().run {
                when (this) {
                    is SampleResult.Success -> {
                        if (response?.version == BuildConfig.VERSION_NAME)
                            loginResultEvent.sendEvent(LoginResult.Success)
                        else
                            loginResultEvent.sendEvent(LoginResult.Update(response?.version?: ""))
                        return@launch
                    }
                    is SampleResult.Error -> {
                        loginResultEvent.sendEvent(LoginResult.NetworkError)
                        return@launch
                    }
                }
            }
        }
    }
}