package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.ListAdapterListMark
import com.example.myapplication.adapters.RecycleAdapterStatisticMark
import com.example.myapplication.data.Mark
import com.example.myapplication.data.StatisticMark
import com.example.myapplication.databinding.FragmentStatisticMarkBinding
import com.example.myapplication.retrofit.RetrofitClient
import com.example.myapplication.viewmodel.SharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StatisticMarkFragment : Fragment() {


    private var binding: FragmentStatisticMarkBinding? = null
    private lateinit var recyclerView: RecyclerView

    private val viewModel: SharedViewModel by activityViewModels()


//    val yourDataList = listOf(
//        StatisticMark(1, 1,"24.05.2024", 121.0, null, "pressure"),
//        StatisticMark(1, 1,"24.05.2024",  120.0, null, "pressure"),
//        StatisticMark(1, 1,"24.05.2024",  120.0, null, "pressure"),
//    )

    private var marks: ArrayList<StatisticMark> = arrayListOf()



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


        RetrofitClient.apiService.getMarksForUser(1, listOf(viewModel.markIdFromListMark.value!!)).enqueue(object : Callback<List<StatisticMark>> {

            override fun onResponse(call: Call<List<StatisticMark>>, response: Response<List<StatisticMark>>) {
                if (response.isSuccessful) {

                    response.body()?.let {
                        marks.clear()

                        val filteredMarks = it.filter { item ->
                            item.parameter_id == viewModel.markIdFromListMark.value!!
                        }

                        marks.addAll(filteredMarks)
                        val adapter = RecycleAdapterStatisticMark(marks)
                        recyclerView.adapter = adapter
                    }

                } else {
                    Log.e("MainActivity", "Failed to load data: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<StatisticMark>>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
            }
        })



//        val adapter = RecycleAdapterStatisticMark(yourDataList)
//        recyclerView.adapter = adapter

        return binding!!.root
    }


}