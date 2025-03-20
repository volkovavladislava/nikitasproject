package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

//    val userId = 1
    val userId = MutableLiveData<Int?>()



    var markIdFromListMark = MutableLiveData<Int>()
    var markNameFromListMark = MutableLiveData<String>()
    var markDescriptionFromListMark = MutableLiveData<String>()

    var formIdFromListForms = MutableLiveData<Int>()
    var formNameFromListForms = MutableLiveData<String>()
    var formDescriptionFromListForms = MutableLiveData<String>()
}