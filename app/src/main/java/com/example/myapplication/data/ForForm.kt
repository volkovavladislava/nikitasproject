package com.example.myapplication.data





data class Question(
    val question_of_interview_id: Int,
    val question_id: Int,
    val question_type: Int,
    val question_name: String,
    val question_description: String?,
    val priority: Int,
    val max_answers: Int,
    val transition_type: Int?,
    val question_options: List<QuestionOption>?,
    val question_conditions: List<QuestionCondition>?
)

data class QuestionOption(
    val option_id: Int,
    val option_type: Int,
    val option_text: String,
    val option_constraint: List<QuestionOptionConstraint>?
)
data class QuestionOptionConstraint(
    val max: Int,
    val min: Int,
    val unit: String
)

data class QuestionCondition(
    val operand_id: Int, // Операнд, например, 2
    val operand_question_id: Int, // ID вопроса, ответ на который проверяется
    val operand_compare_type: Int, // Тип сравнения (например, равенство)
    val q_options: List<ConditionOption> // Список вариантов, которые удовлетворяют условию
)

data class ConditionOption(
    val option_id: Int, // ID варианта ответа
    val text: String // Текст варианта ответа (опционально, используется для отображения или отладки)
)
