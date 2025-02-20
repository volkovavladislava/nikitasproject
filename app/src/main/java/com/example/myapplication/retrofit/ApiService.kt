package com.example.myapplication.retrofit

import com.example.myapplication.data.AddMark
import com.example.myapplication.data.Mark
import com.example.myapplication.data.Question
import com.example.myapplication.data.StatisticMark
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("InterviewStructure/4")
    fun getInterviewStructure(): Call<List<Question>>


    @GET("AccessPoints/GetParametersDescription/")
    fun getMarks(): Call<List<Mark>>


    @POST("AccessPoints/SaveParameters/")
    fun addMark( @Body addMark: AddMark): Call<Void> //Call<Void>

    @GET("AccessPoints/GetUserParameters/")
    fun getMarksForUser(@Query("user_id") userId: Int, @Query("parameter_ids") paramId: List<Int>): Call<List<StatisticMark>>
}