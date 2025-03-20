package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.data.Forms
import com.example.myapplication.viewmodel.SharedViewModel

class ListAdapterListForms(context: Context, dataArrayList: ArrayList<Forms?>?, private val viewModel: SharedViewModel):
ArrayAdapter<Forms?>(context,
R.layout.list_item_list_forms, dataArrayList!!)  {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val listData = getItem(position)

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item_list_forms, parent, false)
        }
        val listTitle = view!!.findViewById<Button>(R.id.listItemListFormsButton)
        listTitle.text = listData!!.name


        listTitle.setOnClickListener{
            viewModel.formIdFromListForms.value = listData.id
            viewModel.formNameFromListForms.value = listData.name
            viewModel.formDescriptionFromListForms.value = listData.description

            val bundle = bundleOf("title" to listData!!.name )

            if (listData!!.is_published == true){
                Navigation.findNavController(view).navigate(R.id.formFragment, bundle)
            }



        }

        return view
    }
}