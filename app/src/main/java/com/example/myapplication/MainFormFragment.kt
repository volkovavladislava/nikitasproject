package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.myapplication.databinding.FragmentListMarkBinding
import com.example.myapplication.databinding.FragmentMainFormBinding
import com.example.myapplication.viewmodel.SharedViewModel


class MainFormFragment : Fragment() {

    private var binding: FragmentMainFormBinding? = null

    private val viewModel: SharedViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainFormBinding.inflate(inflater, container, false)

        binding!!.addFormButton.setOnClickListener {
                view: View->
            Navigation.findNavController(view).navigate(R.id.listBlankFormFragment)
        }

        binding!!.seeFormButton.setOnClickListener {

        }
        return binding!!.root
    }


}