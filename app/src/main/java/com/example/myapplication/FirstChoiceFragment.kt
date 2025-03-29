package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.myapplication.databinding.FragmentFirstChoiceBinding
import com.example.myapplication.databinding.FragmentListMarkBinding


class FirstChoiceFragment : Fragment() {

    private var binding: FragmentFirstChoiceBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstChoiceBinding.inflate(inflater, container, false)

        binding!!.formFirstChoiceButton.setOnClickListener {
                view: View->
            Navigation.findNavController(view).navigate(R.id.mainFormFragment)
        }

        binding!!.markFirstChoiceButton.setOnClickListener {
                view: View->
            Navigation.findNavController(view).navigate(R.id.listMarkFragment)
        }

        return binding!!.root
    }


}