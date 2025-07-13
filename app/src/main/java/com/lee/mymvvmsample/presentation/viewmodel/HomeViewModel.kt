package com.lee.mymvvmsample.presentation.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.lee.mymvvmsample.common.BaseViewModel
import com.lee.mymvvmsample.common.utils.SingleLiveEvent
import com.lee.mymvvmsample.domain.model.Image
import com.lee.mymvvmsample.domain.usecase.SearchImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchImagesUseCase: SearchImagesUseCase
) : BaseViewModel() {
    
    sealed class SearchResult {
        object Success : SearchResult()
        class Fail(val errorMsg: String) : SearchResult()
        object NetworkError : SearchResult()
    }

    val searchResultEvent = SingleLiveEvent<SearchResult>()

    var etStr: String = ""
    var page: Int = 1

    var imageList: MutableList<Image> = mutableListOf()

    var resetList = MutableLiveData<Boolean>().apply {
        value = false
    }

    /**
     * 이미지 검색 api 호출.
     */
    private fun searchImage(query: String, page: Int) {
        uiScope.launch {
            searchImagesUseCase(query, page).fold(
                onSuccess = { result ->
                    if (result.images.isEmpty()) {
                        searchResultEvent.sendEvent(SearchResult.Fail("검색 결과가 없습니다"))
                    } else {
                        imageList = result.images.toMutableList()
                        searchResultEvent.sendEvent(SearchResult.Success)
                    }
                },
                onFailure = { exception ->
                    searchResultEvent.sendEvent(SearchResult.NetworkError)
                }
            )
        }
    }

    /**
     * Search 버튼 클릭 처리.
     */
    fun onClickSearch() {
        if (!TextUtils.isEmpty(etStr)) {
            page = 1
            resetList.value = true
            searchImage(etStr, page)
        }
    }

    fun onLoadContinue() {
        if (!TextUtils.isEmpty(etStr)) {
            page += 1
            searchImage(etStr, page)
        }
    }
}