package com.lee.mymvvmsample.ui.main.home

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.lee.mymvvmsample.common.BaseViewModel
import com.lee.mymvvmsample.common.utils.Constants
import com.lee.mymvvmsample.common.utils.SingleLiveEvent
import com.lee.mymvvmsample.network.NetworkRepository
import com.lee.mymvvmsample.network.NetworkResult
import com.lee.mymvvmsample.network.model.ImageHits
import com.lee.mymvvmsample.network.model.RequestImageParam
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: NetworkRepository) : BaseViewModel() {
    sealed class SearchResult {
        object Success : SearchResult()
        class Fail(val errorMsg: String) : SearchResult()
        object NetworkError : SearchResult()
    }

    val searchResultEvent = SingleLiveEvent<SearchResult>()

    var etStr: String = ""
    var mPage: Int = 1

    var imageListData: MutableList<ImageHits>? = mutableListOf()

    var resetList = MutableLiveData<Boolean>().apply {
        value = false
    }

    /**
     * 이미지 검색 api 호출.
     */
    private fun searchImage(_query: String, _page: Int) {
        uiScope.launch {
            var params = RequestImageParam().apply {
                key = Constants.PIXABAY_KEY
                q = _query
                image_type = "photo"
                page = _page
                per_page = 20
            }
//            repository.searchImage(params).run {
//                when (this) {
//                    is NetworkResult.Success -> {
//                        if (this.response?.hits!!.isEmpty()) {
//                            searchResultEvent.sendEvent(SearchResult.Fail(""))
//                        } else {
//                            imageListData = response?.hits
//
//                            searchResultEvent.sendEvent(SearchResult.Success)
//                        }
//                    }
//                    is NetworkResult.Error -> {
//                        searchResultEvent.sendEvent(SearchResult.NetworkError)
//                        return@launch
//                    }
//                }
//            }

            repository.searchImage(params)
                .onSuccess {
                    if (this.response.body()?.hits!!.isEmpty()) {
                        searchResultEvent.sendEvent(SearchResult.Fail(""))
                    } else {
                        imageListData = this.response.body()?.hits

                        searchResultEvent.sendEvent(SearchResult.Success)
                    }
                }.onError {
                    searchResultEvent.sendEvent(SearchResult.NetworkError)
                }.onException {
                    this.exception.printStackTrace()
                }
        }
    }

    /**
     * Search 버튼 클릭 처리.
     */
    fun onClickSearch() {
        if (!TextUtils.isEmpty(etStr)) {
            mPage = 1
            resetList.value = true
            searchImage(etStr, mPage)
        }
    }

    fun onLoadContinue() {
        if (!TextUtils.isEmpty(etStr)) {
            mPage += 1
            searchImage(etStr, mPage)
        }
    }
}