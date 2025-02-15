package com.example.myapplication.data

data class StatisticMark(
    val user_id: Int,
    val parameter_id: Int,
    val time: String,
    val value1: Double,
    val value2: Double?,
    val name: String
)
