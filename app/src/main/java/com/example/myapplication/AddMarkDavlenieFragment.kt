package com.example.myapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.myapplication.data.AddMark
import com.example.myapplication.databinding.FragmentAddMarkBinding
import com.example.myapplication.databinding.FragmentAddMarkDavlenieBinding
import com.example.myapplication.retrofit.RetrofitClient.apiService
import com.example.myapplication.viewmodel.SharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class AddMarkDavlenieFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0


    private var binding: FragmentAddMarkDavlenieBinding? = null
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMarkDavlenieBinding.inflate(inflater, container, false)


        val context = activity ?: return binding!!.root
        binding!!.addMarkDavlenieAddDataButton.setOnClickListener{
            getDateTimeCalendar()
            DatePickerDialog(context, this, year, month, day).show()
        }

        binding!!.addMarkDavlenieDataInput.setText(SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date()))

        binding!!.addMarkDavlenieButton.setOnClickListener {

            if (!binding!!.addMarkDavlenieInputSystolic.text.isNullOrEmpty() && !binding!!.addMarkDavlenieInputDiastolic.text.isNullOrEmpty()) {
                val numberValue1 = binding!!.addMarkDavlenieInputSystolic.text.toString().toDouble()
                val numberValue2 = binding!!.addMarkDavlenieInputDiastolic.text.toString().toDouble()
                val date = binding!!.addMarkDavlenieDataInput.text.toString()

                val mark = AddMark(
                    user_id = viewModel.userId,
                    parameter_id= viewModel.markIdFromListMark.value!!,
                    value1 = numberValue1,
                    value2 = numberValue2,
                    time = date
                )
                Log.d("RetrofitClient", "mark " + mark)
                val call: Call<Void> = apiService.addMark(mark)

                call.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            Toast.makeText(context, "Данные успешно добавлены", Toast.LENGTH_SHORT)
                                .show()
                            Log.d("RetrofitClient", "response " + response)
                        } else {
                            Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_SHORT)
                                .show()
                            Log.d("RetrofitClient", "response " + response)
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("RetrofitClient", "Receive user from server problem " + t)
                        Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            else{
                Toast.makeText(context, "Сначала добавьте значение и дату", Toast.LENGTH_SHORT).show()
            }
        }

        return binding!!.root
    }

    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateTimeCalendar()
        TimePickerDialog(context, this, hour, minute, true).show()
    }



    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute

        val formattedDate = String.format(
            Locale.getDefault(),
            "%04d-%02d-%02d %02d:%02d",
            savedYear, savedMonth+1, savedDay, savedHour, savedMinute
        )
        binding!!.addMarkDavlenieDataInput.setText(formattedDate)

//        binding!!.addpromptDate.setText("$savedYear-$savedMonth-$savedDay $savedHour:$savedMinute:00")
    }


}