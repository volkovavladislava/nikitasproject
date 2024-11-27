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
import com.example.myapplication.adapters.RecycleAdapterStatisticMarkDavlenie
import com.example.myapplication.data.StatisticMark
import com.example.myapplication.data.StatisticMarkDavlenie
import com.example.myapplication.databinding.FragmentStatisticMarkBinding
import com.example.myapplication.databinding.FragmentStatisticMarkDavlenieBinding
import com.example.myapplication.viewmodel.SharedViewModel


class StatisticMarkDavlenieFragment : Fragment() {


    private var binding: FragmentStatisticMarkDavlenieBinding? = null
    private lateinit var recyclerView: RecyclerView

    private val viewModel: SharedViewModel by activityViewModels()


    val yourDataList = listOf(
        StatisticMarkDavlenie("24.05.2024", "18:34", "120", "80"),
        StatisticMarkDavlenie("24.05.2024", "18:34", "120", "80"),
        StatisticMarkDavlenie("24.05.2024", "18:34", "120", "80"),
        StatisticMarkDavlenie("24.05.2024", "18:34", "120", "80"),
        StatisticMarkDavlenie("24.05.2024", "18:34", "120", "80")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStatisticMarkDavlenieBinding.inflate(inflater, container, false)

        recyclerView =  binding!!.statisticMarkDavlenieRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)


        binding!!.statisticMarkDavlenieAddDataButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.addMarkDavlenieFragment)
        }


        val adapter = RecycleAdapterStatisticMarkDavlenie(yourDataList)
        recyclerView.adapter = adapter


        return binding!!.root
    }


}