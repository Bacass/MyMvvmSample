package com.lee.mymvvmsample.ui.main.send

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lee.mymvvmsample.network.NetworkRepository
import com.lee.mymvvmsample.common.BaseViewModel

class SendViewModel(private val repository: NetworkRepository) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is send Fragment"
    }
    val text: LiveData<String> = _text
}