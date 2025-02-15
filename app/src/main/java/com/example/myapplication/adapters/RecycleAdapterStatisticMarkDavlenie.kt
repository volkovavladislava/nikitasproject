package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.StatisticMark

import com.example.myapplication.data.StatisticMarkDavlenie
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class RecycleAdapterStatisticMarkDavlenie (private var dataList: List<StatisticMark>) :
    RecyclerView.Adapter<RecycleAdapterStatisticMarkDavlenie.ViewHolderClass>() {

    class ViewHolderClass(view: View) : RecyclerView.ViewHolder(view) {
        val dateTextView: TextView = view.findViewById(R.id.date_text)
        val timeTextView: TextView = view.findViewById(R.id.time_text)
        val metricText1View: TextView = view.findViewById(R.id.metric_text1)
        val metricText2View: TextView = view.findViewById(R.id.metric_text2)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_list_item_statistic_mark_davlenie, parent, false)
        return ViewHolderClass(view)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val metric = dataList[position]
        holder.dateTextView.text = formatDate(metric.time)
        holder.timeTextView.text = formatTime(metric.time)
        holder.metricText1View.text = String.format(Locale.US, "%,.0f", metric.value1)
        holder.metricText2View.text = String.format(Locale.US, "%,.0f", metric.value2)
//        holder.metricText1View.text = "1"
//        holder.metricText2View.text = "2"
    }

    override fun getItemCount(): Int {
        return dataList.size
    }



    fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        outputFormat.timeZone = TimeZone.getTimeZone("Asia/Singapore")
        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date)
    }


    fun formatTime(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        outputFormat.timeZone = TimeZone.getTimeZone("Asia/Singapore")
        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date)
    }
}