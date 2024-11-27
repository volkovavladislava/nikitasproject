package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

import com.example.myapplication.data.StatisticMarkDavlenie

class RecycleAdapterStatisticMarkDavlenie (private var dataList: List<StatisticMarkDavlenie>) :
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
        holder.dateTextView.text = metric.date
        holder.timeTextView.text = metric.time
        holder.metricText1View.text = metric.metric1
        holder.metricText2View.text = metric.metric2
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}