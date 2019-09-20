package com.lee.mymvvmsample.ui.main.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lee.mymvvmsample.network.NetworkRepository
import com.lee.mymvvmsample.common.BaseViewModel

class GalleryViewModel(private val repository: NetworkRepository) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}