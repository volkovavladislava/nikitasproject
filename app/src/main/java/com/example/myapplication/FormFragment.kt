package com.example.myapplication

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.myapplication.data.Question
import com.example.myapplication.databinding.FragmentFormBinding
import com.google.gson.Gson


class FormFragment : Fragment() {

    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layout = binding.dynamicFormLayout

        val json = """
            [
              {"question_of_interview_id": 16, "question_id": 41, "question_name": "Как к Вам лучше обращаться?", "question_description": "вопрос об имени", "priority": 1, "transition_type": 1, "question_options": null, "question_conditions": null},
              {"question_of_interview_id": 8, "question_id": 15, "question_name": "Какой пол мне указать в анкете?", "question_description": null, "priority": 2, "transition_type": 1, "question_options": [{"option_id": 7, "option_text": "Мужской"}, {"option_id": 8, "option_text": "Женский"}], "question_conditions": null},
              {"question_of_interview_id": 4, "question_id": 4, "question_name": "Являетесь ли вы студентом?", "question_description": null, "priority": 4, "transition_type": 1, "question_options": [{"option_id": 4, "option_text": "Да"}, {"option_id": 5, "option_text": "Нет"}], "question_conditions": null},
              {"question_of_interview_id": 15, "question_id": 38, "question_name": "Что Вы курите (сигареты, вейп и т.п.)", "question_description": null, "priority": 5, "transition_type": null, "question_options": [{"option_id": 39, "option_text": "сигареты"}, {"option_id": 40, "option_text": "вейп"}, {"option_id": 41, "option_text": "айкос"}], "question_conditions": null},
              {"question_of_interview_id": 1, "question_id": 1, "question_name": "Если Вы студент, подскажите пожалуйста где проживаете в настоящее время?", "question_description": "", "priority": 6, "transition_type": 1, "question_options": [{"option_id": 1, "option_text": "в общежити"}, {"option_id": 2, "option_text": "снимаю квартиру"}, {"option_id": 3, "option_text": "проживаю с родителями"}], "question_conditions": null},
              {"question_of_interview_id": 2, "question_id": 2, "question_name": "Достаточная ли у Вас по Вашему мнению физическая активность?", "question_description": null, "priority": 10, "transition_type": 1, "question_options": [{"option_id": 4, "option_text": "Да"}, {"option_id": 5, "option_text": "Нет"}], "question_conditions": null},
              {"question_of_interview_id": 7, "question_id": 10, "question_name": "Были ли у ваших родителей, бабушек, дедушек инфаркты, инсульты, случаи внезапной смерти в возрасте <55 лет для мужчин и в возрасте <65 лет для женщин?", "question_description": null, "priority": 11, "transition_type": 1, "question_options": [{"option_id": 4, "option_text": "Да"}, {"option_id": 5, "option_text": "Нет"}, {"option_id": 6, "option_text": "Не знаю"}], "question_conditions": null},
              {"question_of_interview_id": 6, "question_id": 9, "question_name": "Слышали ли вы от матери или отца, что они принимают лекарства от высокого давления?", "question_description": null, "priority": 11, "transition_type": 1, "question_options": [{"option_id": 4, "option_text": "Да"}, {"option_id": 5, "option_text": "Нет"}, {"option_id": 6, "option_text": "Не знаю"}], "question_conditions": null},
              {"question_of_interview_id": 5, "question_id": 8, "question_name": "Имеется ли у матери или отца повышенное артериальное давление?", "question_description": null, "priority": 11, "transition_type": 1, "question_options": [{"option_id": 4, "option_text": "Да"}, {"option_id": 5, "option_text": "Нет"}, {"option_id": 6, "option_text": "Не знаю"}], "question_conditions": null},
              {"question_of_interview_id": 12, "question_id": 16, "question_name": "Курите ли Вы в настоящее время?", "question_description": "начала ветвления (Курите ли Вы в настоящее время?)", "priority": 20, "transition_type": 1, "question_options": [{"option_id": 4, "option_text": "Да"}, {"option_id": 5, "option_text": "Нет"}], "question_conditions": null},
              {"question_of_interview_id": 13, "question_id": 17, "question_name": "Вы курите сигареты или используете альтернативные системы нагревания табака?", "question_description": "", "priority": 21, "transition_type": 2, "question_options": [{"option_id": 5, "option_text": "Нет"}, {"option_id": 6, "option_text": "Не знаю"}], "question_conditions": [{"operand_id": 2, "operand_question_id": 12, "operand_compare_type": 1, "q_options": [{"option_id": 4, "text": "Да"}]}]}
            ]
        """


        val gson = Gson()
        val questions: List<Question> = gson.fromJson(json, Array<Question>::class.java).toList()


        questions.forEach { question ->
            // Создаем TextView для вопроса
            val questionTextView = TextView(requireContext()).apply {
                text = question.question_name
                textSize = 18f

                setPadding(0, 24, 0, 24)
            }
            layout.addView(questionTextView)

            // Если у вопроса есть варианты ответа, создаем RadioGroup
            question.question_options?.let { options ->
                val radioGroup = RadioGroup(requireContext()).apply {
                    orientation = RadioGroup.VERTICAL
                    setPadding(0, 24, 0, 24)
                }
                options.forEach { option ->
                    val radioButton = RadioButton(requireContext()).apply {
                        text = option.option_text
                    }
                    radioGroup.addView(radioButton)
                }
                layout.addView(radioGroup)
            } ?: run {
                // Если нет вариантов ответа, создаем EditText для ввода
                val answerInput = EditText(requireContext()).apply {
                    hint = "Ваш ответ"
                }
                layout.addView(answerInput)
            }


        }
        val confirmButton = Button(requireContext()).apply {
            text = "ОТПРАВИТЬ ДАННЫЕ"
            setTextColor(Color.parseColor("#FFFFFF")) // White text color
            textSize = 16f // Text size
            typeface = Typeface.create("sans-serif-medium", Typeface.BOLD) // Font family and bold style
            setPadding(0, 16, 0, 16) // Padding inside the button
            setBackgroundColor(Color.parseColor("#00D0A0")) // Background color (green)
            backgroundTintList = ColorStateList.valueOf(Color.parseColor("#00D0A0")) // Button background tint
            stateListAnimator = null // Removes the button elevation shadow
        }


        val shapeDrawable = GradientDrawable().apply {
            cornerRadius = 20f // Set the corner radius to 20dp
            setColor(Color.parseColor("#00D0A0")) // Background color (green)
        }
        confirmButton.background = shapeDrawable

        layout.addView(confirmButton)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


