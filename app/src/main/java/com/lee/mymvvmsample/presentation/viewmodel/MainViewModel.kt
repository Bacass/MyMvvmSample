package com.lee.mymvvmsample.presentation.viewmodel

import com.lee.mymvvmsample.common.BaseViewModel
import com.lee.mymvvmsample.common.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor() : BaseViewModel() {
        sealed class LoginResult {
            object Success : LoginResult()

            class Fail(val errorMsg: String) : LoginResult()

            object NetworkError : LoginResult()

            class ServerError(val errorMsg: String) : LoginResult()

            class Update(val newVersion: String) : LoginResult()
        }

        val loginResultEvent = SingleLiveEvent<LoginResult>()

//    fun processVersion() {
//        uiScope.launch {
//            repository.serviceVersion().run {
//                when (this) {
//                    is NetworkResult.Success -> {
//                        if (response?.version == BuildConfig.VERSION_NAME)
//                            loginResultEvent.sendEvent(LoginResult.Success)
//                        else
//                            loginResultEvent.sendEvent(LoginResult.Update(response?.version?: ""))
//                        return@launch
//                    }
//                    is NetworkResult.Error -> {
//                        loginResultEvent.sendEvent(LoginResult.NetworkError)
//                        return@launch
//                    }
//                }
//            }
//        }
//    }
    }
