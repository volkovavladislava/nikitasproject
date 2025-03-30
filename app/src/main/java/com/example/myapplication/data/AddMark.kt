package com.example.myapplication.data

import java.util.Date

data class AddMark(
    val user_id: Int,
    val parameter_id: Int,
    val value1: Double,
    val value2: Double?,
    val time: String
)
