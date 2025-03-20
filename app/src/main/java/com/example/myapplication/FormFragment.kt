package com.example.myapplication

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.myapplication.data.Question
import com.example.myapplication.databinding.FragmentFormBinding
import com.example.myapplication.functions.populateDynamicFormLayout
import com.example.myapplication.retrofit.RetrofitClient
import com.example.myapplication.viewmodel.SharedViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FormFragment : Fragment() {

    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!


    private val viewModel: SharedViewModel by activityViewModels()

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

        RetrofitClient.apiService.getInterviewStructure(viewModel.formIdFromListForms.value!!).enqueue(object :
            Callback<List<Question>> {
            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {
                if (response.isSuccessful) {
                    val questions = response.body()
                    questions?.let {

                        val json = Gson().toJson(it)
                        Log.d("RetrofitClient", "json " + json)

                        populateDynamicFormLayout(requireContext(), layout, json)
                    }
                }
            }
            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                Log.d("RetrofitClient", "Receive user from server problem " + t)
                t.printStackTrace() // Обработка ошибок
            }
        })

//        val json = """
//            [
//              {"question_of_interview_id": 16, "question_id": 41, "question_name": "Как к Вам лучше обращаться?", "question_description": "вопрос об имени", "priority": 1, "transition_type": 1, "question_options": null, "question_conditions": null},
//              {"question_of_interview_id": 8, "question_id": 15, "question_name": "Какой пол мне указать в анкете?", "question_description": null, "priority": 2, "transition_type": 1, "question_options": [{"option_id": 7, "option_text": "Мужской"}, {"option_id": 8, "option_text": "Женский"}], "question_conditions": null},
//              {"question_of_interview_id": 4, "question_id": 4, "question_name": "Являетесь ли вы студентом?", "question_description": null, "priority": 4, "transition_type": 1, "question_options": [{"option_id": 4, "option_text": "Да"}, {"option_id": 5, "option_text": "Нет"}], "question_conditions": null},
//              {"question_of_interview_id": 15, "question_id": 38, "question_name": "Что Вы курите (сигареты, вейп и т.п.)", "question_description": null, "priority": 5, "transition_type": null, "question_options": [{"option_id": 39, "option_text": "сигареты"}, {"option_id": 40, "option_text": "вейп"}, {"option_id": 41, "option_text": "айкос"}], "question_conditions": null},
//              {"question_of_interview_id": 1, "question_id": 1, "question_name": "Если Вы студент, подскажите пожалуйста где проживаете в настоящее время?", "question_description": "", "priority": 6, "transition_type": 1, "question_options": [{"option_id": 1, "option_text": "в общежити"}, {"option_id": 2, "option_text": "снимаю квартиру"}, {"option_id": 3, "option_text": "проживаю с родителями"}], "question_conditions": null},
//              {"question_of_interview_id": 2, "question_id": 2, "question_name": "Достаточная ли у Вас по Вашему мнению физическая активность?", "question_description": null, "priority": 10, "transition_type": 1, "question_options": [{"option_id": 4, "option_text": "Да"}, {"option_id": 5, "option_text": "Нет"}], "question_conditions": null},
//              {"question_of_interview_id": 7, "question_id": 10, "question_name": "Были ли у ваших родителей, бабушек, дедушек инфаркты, инсульты, случаи внезапной смерти в возрасте <55 лет для мужчин и в возрасте <65 лет для женщин?", "question_description": null, "priority": 11, "transition_type": 1, "question_options": [{"option_id": 4, "option_text": "Да"}, {"option_id": 5, "option_text": "Нет"}, {"option_id": 6, "option_text": "Не знаю"}], "question_conditions": null},
//              {"question_of_interview_id": 6, "question_id": 9, "question_name": "Слышали ли вы от матери или отца, что они принимают лекарства от высокого давления?", "question_description": null, "priority": 11, "transition_type": 1, "question_options": [{"option_id": 4, "option_text": "Да"}, {"option_id": 5, "option_text": "Нет"}, {"option_id": 6, "option_text": "Не знаю"}], "question_conditions": null},
//              {"question_of_interview_id": 5, "question_id": 8, "question_name": "Имеется ли у матери или отца повышенное артериальное давление?", "question_description": null, "priority": 11, "transition_type": 1, "question_options": [{"option_id": 4, "option_text": "Да"}, {"option_id": 5, "option_text": "Нет"}, {"option_id": 6, "option_text": "Не знаю"}], "question_conditions": null},
//              {"question_of_interview_id": 12, "question_id": 16, "question_name": "Курите ли Вы в настоящее время?", "question_description": "начала ветвления (Курите ли Вы в настоящее время?)", "priority": 20, "transition_type": 1, "question_options": [{"option_id": 4, "option_text": "Да"}, {"option_id": 5, "option_text": "Нет"}], "question_conditions": null},
//              {"question_of_interview_id": 13, "question_id": 17, "question_name": "Вы курите сигареты или используете альтернативные системы нагревания табака?", "question_description": "", "priority": 21, "transition_type": 2, "question_options": [{"option_id": 5, "option_text": "Нет"}, {"option_id": 6, "option_text": "Не знаю"}], "question_conditions": [{"operand_id": 2, "operand_question_id": 12, "operand_compare_type": 1, "q_options": [{"option_id": 4, "text": "Да"}]}]}
//            ]
//        """
//        populateDynamicFormLayout(requireContext(), layout, json)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


