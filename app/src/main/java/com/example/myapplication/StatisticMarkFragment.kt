package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.RecycleAdapterStatisticMark
import com.example.myapplication.data.StatisticMark
import com.example.myapplication.databinding.FragmentStatisticMarkBinding
import com.example.myapplication.viewmodel.SharedViewModel


class StatisticMarkFragment : Fragment() {


    private var binding: FragmentStatisticMarkBinding? = null
    private lateinit var recyclerView: RecyclerView

    private val viewModel: SharedViewModel by activityViewModels()


    val yourDataList = listOf(
        StatisticMark("24.05.2024", "18:34", "120"),
        StatisticMark("24.05.2024", "18:34", "120"),
        StatisticMark("24.05.2024", "18:34", "120"),
        StatisticMark("24.05.2024", "18:34", "120"),
        StatisticMark("24.05.2024", "18:34", "120")
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentStatisticMarkBinding.inflate(inflater, container, false)

        recyclerView =  binding!!.statisticMarkRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)


        binding!!.statisticMarkAddDataButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.addMarkFragment)
        }


        val adapter = RecycleAdapterStatisticMark(yourDataList)
        recyclerView.adapter = adapter

        return binding!!.root
    }


}