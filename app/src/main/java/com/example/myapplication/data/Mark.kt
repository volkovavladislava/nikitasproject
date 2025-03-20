package com.example.myapplication.data

data class Mark(
    val id: Int,
    val name: String,
    val description: String,
    val measurement_unit_id: Int,
    val received_type_id: Int,
    val count: Int,
    val type_id: Int,
    val modifiable: Boolean,
    val frequency_rate_id: Int,
    val show_priority: Int,


)
