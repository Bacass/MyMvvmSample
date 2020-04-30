package com.lee.mymvvmsample.ui.main

import com.lee.mymvvmsample.BuildConfig
import com.lee.mymvvmsample.network.NetworkRepository
import com.lee.mymvvmsample.network.NetworkResult
import com.lee.mymvvmsample.common.BaseViewModel
import com.lee.mymvvmsample.common.utils.Constants
import com.lee.mymvvmsample.common.utils.SingleLiveEvent
import com.lee.mymvvmsample.network.model.RequestImageParam
import com.lee.mymvvmsample.network.model.RequestVideoParam
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NetworkRepository): BaseViewModel() {
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

    /**
     * 이미지 검색 api 호출.
     */
    fun searchImage(_query: String, _page: Int, _per_page: Int) {
        uiScope.launch {
            var params = RequestImageParam().apply {
                key = Constants.PIXABAY_KEY
                q = _query
                image_type = "photo"
                page = _page
                per_page = _per_page
            }
            repository.searchImage(params).run {
                when (this) {
                    is NetworkResult.Success -> {

                    }
                    is NetworkResult.Error -> {
                        loginResultEvent.sendEvent(LoginResult.NetworkError)
                        return@launch
                    }
                }
            }
        }
    }

    /**
     * 동영상 검색 api 호출.
     */
    fun searchVideo(_query: String, _page: Int, _per_page: Int) {
        uiScope.launch {
            var params = RequestVideoParam().apply {
                key = Constants.PIXABAY_KEY
                q = _query
                page = _page
                per_page = _per_page
            }
            repository.searchVideo(params).run {
                when (this) {
                    is NetworkResult.Success -> {

                    }
                    is NetworkResult.Error -> {
                        loginResultEvent.sendEvent(LoginResult.NetworkError)
                        return@launch
                    }
                }
            }
        }
    }
}