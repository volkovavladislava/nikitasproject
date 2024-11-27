package com.example.myapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import com.example.myapplication.databinding.FragmentAddMarkBinding
import com.example.myapplication.databinding.FragmentListMarkBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class AddMarkFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


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


    private var binding: FragmentAddMarkBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMarkBinding.inflate(inflater, container, false)


        val context = activity ?: return binding!!.root
        binding!!.addMarkAddDataButton.setOnClickListener{
            getDateTimeCalendar()
            DatePickerDialog(context, this, year, month, day).show()
        }

        binding!!.addMarkDataInput.setText(SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date()))



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
        binding!!.addMarkDataInput.setText(formattedDate)

//        binding!!.addpromptDate.setText("$savedYear-$savedMonth-$savedDay $savedHour:$savedMinute:00")
    }


}