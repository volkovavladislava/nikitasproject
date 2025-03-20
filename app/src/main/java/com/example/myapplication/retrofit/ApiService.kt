package com.example.myapplication.retrofit

import com.example.myapplication.data.AddMark
import com.example.myapplication.data.Forms
import com.example.myapplication.data.LoginRequest
import com.example.myapplication.data.Mark
import com.example.myapplication.data.Question
import com.example.myapplication.data.RegistrationRequest
import com.example.myapplication.data.StatisticMark
import com.example.myapplication.data.UserAuth
import com.example.myapplication.functions.InterviewResult
import com.example.myapplication.functions.InterviewResultWrapper
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @POST("AccessPoints/GetUserParameters/LoginUser")
    fun login(@Body loginRequest: LoginRequest): Call<UserAuth>

    @POST("AccessPoints/GetUserParameters/RegisterUser")
    fun register(@Body registerRequest: RegistrationRequest): Call<UserAuth>


    @GET("InterviewStructure/{formId}")
    fun getInterviewStructure(@Path("formId") formId: Int): Call<List<Question>>

    @GET("AccessPoints/GetInterviewsDescription/")
    fun getAllInterview(): Call<List<Forms>>


    @GET("AccessPoints/GetParametersDescription/")
    fun getMarks(): Call<List<Mark>>


    @POST("AccessPoints/SaveParameters/")
    fun addMark( @Body addMark: AddMark): Call<Void> //Call<Void>

    @GET("AccessPoints/GetUserParameters/")
    fun getMarksForUser(@Query("user_id") userId: Int, @Query("parameter_ids") paramId: List<Int>): Call<List<StatisticMark>>


    @POST("AccessPoints/SaveInterviewAnswers/")
    fun sendInterviewAnswers(@Body interviewResult: InterviewResultWrapper): Call<ResponseBody>
}