package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.myapplication.adapters.ListAdapterListForms
import com.example.myapplication.adapters.ListAdapterListMark
import com.example.myapplication.data.Forms
import com.example.myapplication.data.Mark
import com.example.myapplication.databinding.FragmentListBlankFormBinding
import com.example.myapplication.databinding.FragmentListMarkBinding
import com.example.myapplication.retrofit.RetrofitClient
import com.example.myapplication.viewmodel.SharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListBlankFormFragment : Fragment() {

    private var binding: FragmentListBlankFormBinding? = null

    private val viewModel: SharedViewModel by activityViewModels()

    private var forms: ArrayList<Forms?> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBlankFormBinding.inflate(inflater, container, false)


        val listView = binding!!.listviewListForms

        RetrofitClient.apiService.getAllInterview().enqueue(object : Callback<List<Forms>> {
            override fun onResponse(call: Call<List<Forms>>, response: Response<List<Forms>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        forms.clear()
                        val filteredMarks = it.filter { form -> form?.is_published == true }
                        if (filteredMarks.isNotEmpty()) {
                            forms.addAll(filteredMarks)
                            forms = ArrayList(forms.sortedBy { it?.description })
                            val adapter = ListAdapterListForms(requireContext(), forms, viewModel)
                            listView.adapter = adapter
                        }
                    }
                } else {
                    Log.e("MainActivity", "Failed to load data: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<Forms>>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
            }
        })


        return binding!!.root
    }


}