package com.lee.mymvvmsample.ui.main.home

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.lee.mymvvmsample.R
import com.lee.mymvvmsample.common.BaseViewModel
import com.lee.mymvvmsample.common.utils.Constants
import com.lee.mymvvmsample.common.utils.SingleLiveEvent
import com.lee.mymvvmsample.network.NetworkRepository
import com.lee.mymvvmsample.network.NetworkResult
import com.lee.mymvvmsample.network.model.*
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(private val repository: NetworkRepository) : BaseViewModel() {
    sealed class SearchResult {
        object Success : SearchResult()
        class Fail(val errorMsg: String) : SearchResult()
        object NetworkError : SearchResult()
    }

    val searchResultEvent = SingleLiveEvent<SearchResult>()

    var selectImage = MutableLiveData<Boolean>().apply { // true: image, false: video
        value = true
    }

    var etStr: String = ""
    var mPage: Int = 1

    var imageListData: MutableList<ImageHits>? = mutableListOf()
    var videoListData: MutableList<VideoHits>? = mutableListOf()

    /**
     * 이미지 검색 api 호출.
     */
    fun searchImage(_query: String, _page: Int) {
        uiScope.launch {
            var params = RequestImageParam().apply {
                key = Constants.PIXABAY_KEY
                q = _query
                image_type = "photo"
                page = _page
                per_page = 30
            }
            repository.searchImage(params).run {
                when (this) {
                    is NetworkResult.Success -> {
//                        imageListData.value?.total = this.response?.total!!
//                        imageListData.value?.totalHits = this.response?.totalHits
//                        Timber.d("Lee imageListData.value?.total: ${Gson().toJson(imageListData.value?.total)}")
//                        Timber.d("Lee imageListData.value?.totalHits: ${Gson().toJson(imageListData.value?.totalHits)}")

                        if (this.response?.hits!!.isEmpty()) {
                            searchResultEvent.sendEvent(SearchResult.Fail(""))
                        } else {
                            imageListData?.addAll(response?.hits)

                            searchResultEvent.sendEvent(SearchResult.Success)
                        }
                    }
                    is NetworkResult.Error -> {
                        searchResultEvent.sendEvent(SearchResult.NetworkError)
                        return@launch
                    }
                }
            }
        }
    }

    /**
     * 동영상 검색 api 호출.
     */
    fun searchVideo(_query: String, _page: Int) {
        uiScope.launch {
            var params = RequestVideoParam().apply {
                key = Constants.PIXABAY_KEY
                q = _query
                page = _page
                per_page = 30
            }

            repository.searchVideo(params).run {
                when (this) {
                    is NetworkResult.Success -> {
//                        if (this.response?.hits!!.isEmpty()) {
//                            searchResultEvent.sendEvent(SearchResult.Fail(""))
//                        } else {
//                            imageListData?.addAll(response?.hits!!)
//
//                            searchResultEvent.sendEvent(SearchResult.Success)
//                        }
                    }
                    is NetworkResult.Error -> {
                        searchResultEvent.sendEvent(SearchResult.NetworkError)
                        return@launch
                    }
                }
            }
        }
    }


    /**
     * Search 버튼 클릭 처리.
     */
    fun onClickSearch() {
        if (!TextUtils.isEmpty(etStr)) {
            if (selectImage.value == true) {
                searchImage(etStr, mPage)
            } else {
                searchVideo(etStr, mPage)
            }
        }
    }

    fun onClickSelectImage() {
        Timber.d("onClickSelectImage()")
        selectImage.value = true
    }

    fun onClickSelectVideo() {
        Timber.d("onClickSelectVideo()")
        selectImage.value = false
    }
}