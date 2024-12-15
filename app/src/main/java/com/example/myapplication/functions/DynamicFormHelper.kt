package com.example.myapplication.functions

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
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

fun populateDynamicFormLayout(context: Context, layout: LinearLayout, json: String) {
    val gson = Gson()
    val questions: List<Question> = gson.fromJson(json, Array<Question>::class.java).toList()

    val sortedQuestions = questions.sortedBy { it.priority }

    layout.removeAllViews()

    // Структура для хранения ответов
    val answers = mutableListOf<Pair<String, String>>() // Список пар (ID вопроса, ответ)

    sortedQuestions.forEach { question ->


        // Создаем TextView для вопроса
        val questionTextView = TextView(context).apply {
            text = question.question_name
            textSize = 18f
            setPadding(0, 24, 0, 24)
            ellipsize = TextUtils.TruncateAt.END
            maxLines = 10
        }
        layout.addView(questionTextView)

        // Если у вопроса есть варианты ответа, создаем RadioGroup
        question.question_options?.let { options ->
            val radioGroup = RadioGroup(context).apply {
                orientation = RadioGroup.VERTICAL
                setPadding(0, 24, 0, 24)
            }
            options.forEach { option ->
                val radioButton = RadioButton(context).apply {
                    text = option.option_text
                    tag = option.option_id.toString()

                }
                radioGroup.addView(radioButton)
            }
            layout.addView(radioGroup)

            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                val selectedRadioButton = radioGroup.findViewById<RadioButton>(checkedId)
                val selectedOptionId = selectedRadioButton.tag.toString() // Получаем ID выбранной опции
                answers.add(Pair(question.question_of_interview_id.toString(), selectedOptionId)) // Сохраняем ID вопроса и ID выбранной опции
            }

        } ?: run {
            // Если нет вариантов ответа, создаем EditText для ввода
            val answerInput = EditText(context).apply {
                hint = "Ваш ответ"
                setPadding(16, 24, 16, 24)
                TextViewCompat.setTextAppearance(this, android.R.style.TextAppearance_Material_Body1)
            }
            layout.addView(answerInput)

            answerInput.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    answers.add(Pair(question.question_of_interview_id.toString(), s.toString())) // Сохраняем введенный текст
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }


    val confirmButton = Button(context).apply {
        text = "ОТПРАВИТЬ ДАННЫЕ"
        setTextColor(Color.parseColor("#FFFFFF")) // Белый текст
        textSize = 16f
        typeface = Typeface.create("sans-serif-medium", Typeface.BOLD)
        setPadding(0, 16, 0, 16)
        setBackgroundColor(Color.parseColor("#00D0A0")) // Зеленый фон
        stateListAnimator = null // Убираем тень
    }

    val shapeDrawable = GradientDrawable().apply {
        cornerRadius = 20f // Закругленные углы
        setColor(Color.parseColor("#00D0A0"))
    }
    confirmButton.background = shapeDrawable

    confirmButton.setOnClickListener {
        sendAnswersToDatabase(answers)
    }

    layout.addView(confirmButton)
}


fun sendAnswersToDatabase(answers: List<Pair<String, String>>) {
    // Код для отправки данных в базу данных
    Log.d("Retrofit", "ans " + answers)
    val answersJson = Gson().toJson(answers)

}