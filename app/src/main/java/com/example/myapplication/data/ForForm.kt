package com.example.myapplication.data



data class QuestionOption(
    val option_id: Int,
    val option_text: String
)

data class Question(
    val question_of_interview_id: Int,
    val question_id: Int,
    val question_name: String,
    val question_description: String?,
    val priority: Int,
    val transition_type: Int?,
    val question_options: List<QuestionOption>?,
    val question_conditions: Any?
)
