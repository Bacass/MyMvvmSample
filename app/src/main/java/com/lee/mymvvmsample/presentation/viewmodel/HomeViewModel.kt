package com.lee.mymvvmsample.presentation.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.lee.mymvvmsample.common.BaseViewModel
import com.lee.mymvvmsample.domain.model.Image
import com.lee.mymvvmsample.domain.model.Either
import com.lee.mymvvmsample.domain.model.Failure
import com.lee.mymvvmsample.domain.usecase.SearchImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val searchImagesUseCase: SearchImagesUseCase,
    ) : BaseViewModel() {
        data class UiState(
            val query: String = "",
            val page: Int = 1,
            val isLoading: Boolean = false,
            val imageList: List<Image> = emptyList(),
            val errorMessage: String? = null,
            val resetList: Boolean = false,
        )

        private val _uiState = MutableStateFlow(UiState())
        val uiState: StateFlow<UiState> = _uiState.asStateFlow()

        /**
         * 이미지 검색 api 호출.
         */
        private fun searchImage(query: String, page: Int) {
            uiScope.launch {
                _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                when (val result = searchImagesUseCase(query, page)) {
                    is Either.Left -> {
                        val failure = result.value
                        val msg = when (failure) {
                            is Failure.NoData -> "검색 결과가 없습니다"
                            is Failure.Network -> "네트워크 오류가 발생했습니다"
                            is Failure.Server -> "서버 오류가 발생했습니다"
                            is Failure.InvalidInput -> failure.message
                            is Failure.Unknown -> failure.message ?: "알 수 없는 오류가 발생했습니다"
                        }
                        _uiState.update { state ->
                            state.copy(isLoading = false, errorMessage = msg, resetList = false)
                        }
                    }
                    is Either.Right -> {
                        val images = result.value.images
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                imageList = state.imageList + images,
                                errorMessage = null,
                                resetList = false,
                            )
                        }
                    }
                }
            }
        }

        /**
         * Search 버튼 클릭 처리.
         */
        fun onClickSearch() {
            val query = _uiState.value.query
            if (!TextUtils.isEmpty(query)) {
                _uiState.update { it.copy(page = 1, imageList = emptyList(), resetList = true) }
                searchImage(query, 1)
            }
        }

        fun onLoadContinue() {
            val query = _uiState.value.query
            if (!TextUtils.isEmpty(query)) {
                val nextPage = _uiState.value.page + 1
                _uiState.update { it.copy(page = nextPage) }
                searchImage(query, nextPage)
            }
        }

        fun onQueryChanged(text: String) {
            _uiState.update { it.copy(query = text) }
        }
    }
