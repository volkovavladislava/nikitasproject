package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    val userId = 1
    var markIdFromListMark = MutableLiveData<Int>()
    var markNameFromListMark = MutableLiveData<String>()
    var markDescriptionFromListMark = MutableLiveData<String>()
}