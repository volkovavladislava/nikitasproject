package com.example.myapplication.functions

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import com.google.gson.Gson
import com.example.myapplication.data.Question
import com.example.myapplication.data.QuestionCondition
import com.example.myapplication.retrofit.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//fun populateDynamicFormLayout(context: Context, layout: LinearLayout, json: String) {
//    val gson = Gson()
//    val questions: List<Question> = gson.fromJson(json, Array<Question>::class.java).toList()
//
//    val sortedQuestions = questions.sortedBy { it.priority }
//
//    layout.removeAllViews()
//
//    // Структура для хранения ответов
//    val answers = mutableListOf<Pair<String, String>>() // Список пар (ID вопроса, ответ)
//
//    sortedQuestions.forEach { question ->
//
//        // Создаем TextView для вопроса
//        val questionTextView = TextView(context).apply {
//            text = question.question_name
//            textSize = 18f
//            setPadding(0, 24, 0, 24)
//            ellipsize = TextUtils.TruncateAt.END
//            maxLines = 10
//        }
//        layout.addView(questionTextView)
//
//        when (question.question_type) {
//            1 -> {
//                // Для question_type = 1 создаем RadioGroup
//                val radioGroup = RadioGroup(context).apply {
//                    orientation = RadioGroup.VERTICAL
//                    setPadding(0, 24, 0, 24)
//                }
//                question.question_options?.forEach { option ->
//                    val radioButton = RadioButton(context).apply {
//                        text = option.option_text
//                        tag = option.option_id.toString()
//                    }
//                    radioGroup.addView(radioButton)
//                }
//                layout.addView(radioGroup)
//
//                radioGroup.setOnCheckedChangeListener { _, checkedId ->
//                    val selectedRadioButton = radioGroup.findViewById<RadioButton>(checkedId)
//                    val selectedOptionId = selectedRadioButton.tag.toString() // Получаем ID выбранной опции
//                    answers.add(Pair(question.question_of_interview_id.toString(), selectedOptionId)) // Сохраняем ID вопроса и ID выбранной опции
//                }
//            }
//            2 -> {
//                // Для question_type = 2 создаем EditText для чисел
//                val answerInput = EditText(context).apply {
//                    hint = "Введите число"
//                    inputType = InputType.TYPE_CLASS_NUMBER
//                    setPadding(16, 24, 16, 24)
//                    TextViewCompat.setTextAppearance(this, android.R.style.TextAppearance_Material_Body1)
//                }
//                layout.addView(answerInput)
//
//                answerInput.addTextChangedListener(object : TextWatcher {
//                    override fun afterTextChanged(s: Editable?) {
//                        if (s.toString().matches(Regex("\\d*"))) {
//                            answers.add(Pair(question.question_of_interview_id.toString(), s.toString())) // Сохраняем введенное число
//                        }
//                    }
//                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//                })
//            }
//
//            3 -> {
//                // Для question_type = 3 создаем EditText для текста
//                val answerInput = EditText(context).apply {
//                    hint = "Введите текст"
//                    inputType = InputType.TYPE_CLASS_TEXT
//                    setPadding(16, 24, 16, 24)
//                    TextViewCompat.setTextAppearance(this, android.R.style.TextAppearance_Material_Body1)
//                }
//                layout.addView(answerInput)
//
//                answerInput.addTextChangedListener(object : TextWatcher {
//                    override fun afterTextChanged(s: Editable?) {
//                        if (s.toString().matches(Regex("[a-zA-Zа-яА-Я\\s]*"))) {
//                            answers.add(Pair(question.question_of_interview_id.toString(), s.toString())) // Сохраняем введенный текст
//                        }
//                    }
//                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//                })
//            }
//        }
//    }
//
//    val confirmButton = Button(context).apply {
//        text = "ОТПРАВИТЬ ДАННЫЕ"
//        setTextColor(Color.parseColor("#FFFFFF")) // Белый текст
//        textSize = 16f
//        typeface = Typeface.create("sans-serif-medium", Typeface.BOLD)
//        setPadding(0, 16, 0, 16)
//        setBackgroundColor(Color.parseColor("#00D0A0")) // Зеленый фон
//        stateListAnimator = null // Убираем тень
//    }
//
//    val shapeDrawable = GradientDrawable().apply {
//        cornerRadius = 20f // Закругленные углы
//        setColor(Color.parseColor("#00D0A0"))
//    }
//    confirmButton.background = shapeDrawable
//
//    confirmButton.setOnClickListener {
//        sendAnswersToDatabase(answers)
//    }
//
//    layout.addView(confirmButton)
//}

//2 версия с динамическими вопросами
//fun populateDynamicFormLayout(context: Context, layout: LinearLayout, json: String) {
//    val gson = Gson()
//
//    val questions: List<Question> = gson.fromJson(json, Array<Question>::class.java).toList()
//    val sortedQuestions = questions.sortedBy { it.priority }
//
//    layout.removeAllViews()
//
//    // Структура для хранения ответов
//    val answers = mutableMapOf<String, String>() // Map для хранения ответов (ID вопроса -> ответ)
//
//    // Храним ссылки на созданные элементы, чтобы управлять их видимостью
//    val questionViews = mutableMapOf<String, Pair<View, View>>()
//
//    sortedQuestions.forEach { question ->
//
//        // Создаем TextView для вопроса
//        val questionTextView = TextView(context).apply {
//            text = question.question_name
//            textSize = 18f
//            setPadding(0, 24, 0, 24)
//            ellipsize = TextUtils.TruncateAt.END
//            maxLines = 10
//            visibility = View.GONE // Скрываем вопрос по умолчанию
//        }
//        layout.addView(questionTextView)
//
//        val inputView: View = when (question.question_type) {
//            1 -> {
//                // Для question_type = 1 создаем RadioGroup
//                val radioGroup = RadioGroup(context).apply {
//                    orientation = RadioGroup.VERTICAL
//                    setPadding(0, 24, 0, 24)
//                    visibility = View.GONE // Скрываем по умолчанию
//                }
//                question.question_options?.forEach { option ->
//                    val radioButton = RadioButton(context).apply {
//                        text = option.option_text
//                        tag = option.option_id.toString()
//                    }
//                    radioGroup.addView(radioButton)
//                }
//
//                radioGroup.setOnCheckedChangeListener { _, checkedId ->
//                    val selectedRadioButton = radioGroup.findViewById<RadioButton>(checkedId)
//                    val selectedOptionId = selectedRadioButton.tag.toString()
//                    answers[question.question_of_interview_id.toString()] = selectedOptionId
//                    updateQuestionVisibility(questions, answers, questionViews)
//                }
//                radioGroup
//            }
//            2 -> {
//                // Для question_type = 2 создаем EditText для чисел
//                val answerInput = EditText(context).apply {
//                    hint = "Введите число"
//                    inputType = InputType.TYPE_CLASS_NUMBER
//                    setPadding(16, 24, 16, 24)
//                    visibility = View.GONE // Скрываем по умолчанию
//                }
//                answerInput.addTextChangedListener(object : TextWatcher {
//                    override fun afterTextChanged(s: Editable?) {
//                        if (s.toString().matches(Regex("\\d*"))) {
//                            answers[question.question_of_interview_id.toString()] = s.toString()
//                            updateQuestionVisibility(questions, answers, questionViews)
//                        }
//                    }
//                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//                })
//                answerInput
//            }
//            3 -> {
//                // Для question_type = 3 создаем EditText для текста
//                val answerInput = EditText(context).apply {
//                    hint = "Введите текст"
//                    inputType = InputType.TYPE_CLASS_TEXT
//                    setPadding(16, 24, 16, 24)
//                    visibility = View.GONE // Скрываем по умолчанию
//                }
//                answerInput.addTextChangedListener(object : TextWatcher {
//                    override fun afterTextChanged(s: Editable?) {
//                        if (s.toString().matches(Regex("[a-zA-Zа-яА-Я\\s]*"))) {
//                            answers[question.question_of_interview_id.toString()] = s.toString()
//                            updateQuestionVisibility(questions, answers, questionViews)
//                        }
//                    }
//                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//                })
//                answerInput
//            }
//            else -> View(context) // Пустая View для неподдерживаемых типов
//        }
//
//        layout.addView(inputView)
//
//        // Сохраняем вопрос и его связанный элемент ввода
//        questionViews[question.question_of_interview_id.toString()] = questionTextView to inputView
//    }
//
//    val confirmButton = Button(context).apply {
//        text = "ОТПРАВИТЬ ДАННЫЕ"
//        setTextColor(Color.parseColor("#FFFFFF")) // Белый текст
//        textSize = 16f
//        typeface = Typeface.create("sans-serif-medium", Typeface.BOLD)
//        setPadding(0, 16, 0, 16)
//        setBackgroundColor(Color.parseColor("#00D0A0")) // Зеленый фон
//        stateListAnimator = null // Убираем тень
//    }
//
//    val shapeDrawable = GradientDrawable().apply {
//        cornerRadius = 20f
//        setColor(Color.parseColor("#00D0A0"))
//        setStroke(2, Color.parseColor("#00A080")) // Граница, если нужно
//    }
//
//
//
//    confirmButton.background = shapeDrawable
//
//
//    confirmButton.setOnClickListener {
//        sendAnswersToDatabase(answers.toList())
//    }
//
//    layout.addView(confirmButton)
//
//    // Устанавливаем начальную видимость вопросов
//    updateQuestionVisibility(questions, answers, questionViews)
//}
//
//// Функция для обновления видимости вопросов
//private fun updateQuestionVisibility(
//    questions: List<Question>,
//    answers: Map<String, String>,
//    questionViews: Map<String, Pair<View, View>>
//) {
//    questions.forEach { question ->
//        val shouldDisplay = question.question_conditions?.all { condition ->
//            val relatedAnswer = answers[condition.operand_question_id.toString()]
//            if (relatedAnswer != null) {
//                condition.q_options?.any { it.option_id.toString() == relatedAnswer } ?: false
//            } else {
//                false
//            }
//        } ?: true // Если conditions == null, вопрос всегда отображается
//
//        val views = questionViews[question.question_of_interview_id.toString()]
//        if (views != null) {
//            val (textView, inputView) = views
//            textView.visibility = if (shouldDisplay) View.VISIBLE else View.GONE
//            inputView.visibility = if (shouldDisplay) View.VISIBLE else View.GONE
//        }
//    }
//}
//
//
//
//
//fun sendAnswersToDatabase(answers: List<Pair<String, String>>) {
//    // Код для отправки данных в базу данных
//    Log.d("Retrofit", "ans " + answers)
//    val answersJson = Gson().toJson(answers)
//
//}


fun populateDynamicFormLayout(context: Context, layout: LinearLayout, json: String) {
    val gson = Gson()
    val questions: List<Question> = gson.fromJson(json, Array<Question>::class.java).toList()

    val sortedQuestions = questions.sortedBy { it.priority }
    layout.removeAllViews()

    // Храним ответы в нужном формате
    val answers = mutableMapOf<String, AnswerFormat>()

    // Храним ссылки на элементы для управления видимостью
    val questionViews = mutableMapOf<String, Pair<View, View>>()

    sortedQuestions.forEach { question ->

        val questionTextView = TextView(context).apply {
            text = question.question_name
            textSize = 18f
            setPadding(0, 24, 0, 24)
            maxLines = 10
            visibility = View.GONE
        }
        layout.addView(questionTextView)

        val inputView: View = when (question.question_type) {
            1 -> { // RadioGroup (варианты ответов)
                val radioGroup = RadioGroup(context).apply {
                    orientation = RadioGroup.VERTICAL
                    visibility = View.GONE
                }
                question.question_options?.forEach { option ->
                    val radioButton = RadioButton(context).apply {
                        text = option.option_text
                        tag = option.option_id.toString()
                    }
                    radioGroup.addView(radioButton)
                }

                radioGroup.setOnCheckedChangeListener { _, checkedId ->
                    val selectedRadioButton = radioGroup.findViewById<RadioButton>(checkedId)
                    val selectedOptionId = selectedRadioButton.tag.toString().toInt()
                    answers[question.question_of_interview_id.toString()] = AnswerFormat(
                        question_of_interview_id = question.question_of_interview_id,
                        question_id = question.question_id,
                        option_id = listOf(selectedOptionId),
                        answer_text = "" // `answer_text` всегда присутствует
                    )
                    updateQuestionVisibility(questions, answers, questionViews)
                }
                radioGroup
            }
            2, 3 -> { // EditText (число или текст)
                val answerInput = EditText(context).apply {
                    hint = if (question.question_type == 2) "Введите число" else "Введите текст"
                    inputType = if (question.question_type == 2) InputType.TYPE_CLASS_NUMBER else InputType.TYPE_CLASS_TEXT
                    setPadding(16, 24, 16, 24)
                    visibility = View.GONE
                }
                answerInput.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        val text = s.toString()
                        if (question.question_type == 2 && !text.matches(Regex("\\d*"))) return
                        if (question.question_type == 3 && !text.matches(Regex("[a-zA-Zа-яА-Я\\s]*"))) return

                        answers[question.question_of_interview_id.toString()] = AnswerFormat(
                            question_of_interview_id = question.question_of_interview_id,
                            question_id = question.question_id,
                            option_id = emptyList(),
                            answer_text = text // Всегда присутствует, даже если пустая строка
                        )
                        updateQuestionVisibility(questions, answers, questionViews)
                    }
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                })
                answerInput
            }
            else -> View(context)
        }

        layout.addView(inputView)
        questionViews[question.question_of_interview_id.toString()] = questionTextView to inputView
    }

    val confirmButton = Button(context).apply {
        text = "ОТПРАВИТЬ ДАННЫЕ"
        setTextColor(Color.WHITE)
        textSize = 16f
        setPadding(0, 16, 0, 16)
        setBackgroundColor(Color.parseColor("#00D0A0"))
    }

    confirmButton.setOnClickListener {
        sendAnswersToDatabase(answers.values.toList()) // Отправляем готовый список
    }

    layout.addView(confirmButton)
    updateQuestionVisibility(questions, answers, questionViews)
}

// **Функция отправки ответов **
fun sendAnswersToDatabase(answers: List<AnswerFormat>) {
    val interviewResult = InterviewResultWrapper(
        interview_result = InterviewResult(
            interview_id = 4, // Укажи реальный ID интервью
            user_id = 1,      // Укажи ID пользователя
            answers = answers
        )
    )

    val answersJson = Gson().toJson(interviewResult)
    Log.d("Retrofit", "ans $answersJson")

    RetrofitClient.apiService.sendInterviewAnswers(interviewResult).enqueue(object :
        Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                Log.d("RetrofitClient", "Answers successfully sent!")
            } else {
                Log.e("RetrofitClient", "Failed to send answers. Code: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            Log.e("RetrofitClient", "Error sending answers: ${t.message}")
        }
    })
}


// Функция для обновления видимости вопросов
private fun updateQuestionVisibility(
    questions: List<Question>,
    answers: Map<String, AnswerFormat>,
    questionViews: Map<String, Pair<View, View>>
) {
    questions.forEach { question ->
        val shouldDisplay = question.question_conditions?.all { condition ->
            val relatedAnswer = answers[condition.operand_question_id.toString()]
            if (relatedAnswer != null) {
                condition.q_options?.any { it.option_id in relatedAnswer.option_id } ?: false
            } else {
                false
            }
        } ?: true // Если conditions == null, вопрос всегда отображается

        val views = questionViews[question.question_of_interview_id.toString()]
        if (views != null) {
            val (textView, inputView) = views
            textView.visibility = if (shouldDisplay) View.VISIBLE else View.GONE
            inputView.visibility = if (shouldDisplay) View.VISIBLE else View.GONE
        }
    }
}

// Структура ответа
data class AnswerFormat(
    val question_of_interview_id: Int,
    val question_id: Int,
    val option_id: List<Int>,
    val answer_text: String?
)

data class InterviewResult(
    val interview_id: Int,
    val user_id: Int,
    val answers: List<AnswerFormat>
)

data class InterviewResultWrapper(
    val interview_result: InterviewResult
)


