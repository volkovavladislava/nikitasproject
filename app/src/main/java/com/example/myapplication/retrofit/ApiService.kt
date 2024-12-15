package com.example.myapplication.retrofit

import com.example.myapplication.data.AddMark
import com.example.myapplication.data.Mark
import com.example.myapplication.data.Question
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("InterviewStructure/4")
    fun getInterviewStructure(): Call<List<Question>>


    @GET("AccessPoints/ParametersDescription/")
    fun getMarks(): Call<List<Mark>>


    @POST("AccessPoints/Parameters/")
    fun addMark( @Body addMark: AddMark): Call<Void> //Call<Void>
}