package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    var markIdFromListMark = MutableLiveData<Int>()
    var markNameFromListMark = MutableLiveData<String>()
}