package com.example.myapplication.retrofit

import com.example.myapplication.data.Question
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("InterviewStructure/4")
    fun getInterviewStructure(): Call<List<Question>>
}