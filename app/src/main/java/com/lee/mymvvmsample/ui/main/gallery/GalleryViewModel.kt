package com.lee.mymvvmsample.ui.main.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lee.mymvvmsample.network.NetworkRepositoryImpl
import com.lee.mymvvmsample.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: NetworkRepositoryImpl) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}