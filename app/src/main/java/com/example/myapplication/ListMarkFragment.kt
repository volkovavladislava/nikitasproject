package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.ListAdapterListMark
import com.example.myapplication.data.Mark
import com.example.myapplication.data.StatisticMark
import com.example.myapplication.databinding.FragmentListMarkBinding
import com.example.myapplication.viewmodel.SharedViewModel


class ListMarkFragment : Fragment() {

    private var binding: FragmentListMarkBinding? = null
    private lateinit var recyclerView: RecyclerView

    private val viewModel: SharedViewModel by activityViewModels()

    private var check = 0

    val marks: ArrayList<Mark?>? = arrayListOf(
        Mark(1, "Давление"),
        Mark(2, "Пульс"),
        Mark(3, "Холестерин"),
        Mark(4, "Глюкоза"),
        Mark(5, "Сатурация"),
    )


//    val yourDataList = listOf(
//        StatisticMark("24.05.2024", "18:34", "120", "80"),
//        StatisticMark("24.05.2024", "18:34", "120", "80"),
//        StatisticMark("24.05.2024", "18:34", "120", "80"),
//        StatisticMark("24.05.2024", "18:34", "120", "80"),
//        StatisticMark("24.05.2024", "18:34", "120", "80")
//    )
//
//
//    val yourDataList2 = listOf(
//        StatisticMark("24.05.2024", "18:34", "120", "0"),
//        StatisticMark("24.05.2024", "18:34", "120", "0"),
//        StatisticMark("24.05.2024", "18:34", "120", "0"),
//        StatisticMark("24.05.2024", "18:34", "120", "0"),
//        StatisticMark("24.05.2024", "18:34", "120", "0")
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListMarkBinding.inflate(inflater, container, false)


        val a = binding!!.listviewListMarks
        a.apply {
            adapter =  ListAdapterListMark(this.context, marks ,viewModel)
            isClickable = true}












//        recyclerView =  binding!!.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        val adapter = RecycleAdapterStatisticMark1(yourDataList2)
//        recyclerView.adapter = adapter
//
//
//
//        binding?.tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                when (tab.position) {
//                    0 -> loadCholesterolData()  // Загружаем данные по холестерину
//                    1 -> loadBloodPressureData()  // Загружаем данные по кровяному давлению
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab) {}
//            override fun onTabReselected(tab: TabLayout.Tab) {}
//        })
//
//
//        binding!!.addDataButton.setOnClickListener {
//                view: View->
//            if(check ==0){
//                Navigation.findNavController(view).navigate(R.id.addMarkFragment)
//            }
//            else{
//                Navigation.findNavController(view).navigate(R.id.addMarkDavlenieFragment)
//            }
//
//
//        }




        return binding!!.root
//        return inflater.inflate(R.layout.fragment_list_mark, container, false)
    }



//    private fun loadCholesterolData() {
//        val adapter = recyclerView.adapter as RecycleAdapterStatisticMark1
//        check = 0
//        adapter.updateData(yourDataList2)
//    }
//
//    private fun loadBloodPressureData() {
//        val adapter = recyclerView.adapter as RecycleAdapterStatisticMark1
//        check = 1
//        adapter.updateData(yourDataList)
//    }


}