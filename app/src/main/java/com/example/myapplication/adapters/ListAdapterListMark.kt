package com.example.myapplication.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.data.Mark
import com.example.myapplication.viewmodel.SharedViewModel

class ListAdapterListMark(context: Context, dataArrayList: ArrayList<Mark?>?, private val viewModel: SharedViewModel):
    ArrayAdapter<Mark?>(context,
    R.layout.list_item_list_mark, dataArrayList!!) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val listData = getItem(position)

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item_list_mark, parent, false)
        }
        val listTitle = view!!.findViewById<Button>(R.id.listItemListMarkButtom)
        listTitle.text = listData!!.description


        listTitle.setOnClickListener{
            viewModel.markIdFromListMark.value = listData.id
            viewModel.markNameFromListMark.value = listData.name
            viewModel.markDescriptionFromListMark.value = listData.description

            val bundle = bundleOf("title" to listData!!.description )

            if (listData!!.id == 1){

                Navigation.findNavController(view).navigate(R.id.statisticMarkDavlenieFragment, bundle)
            }
            else{
                Navigation.findNavController(view).navigate(R.id.statisticMarkFragment, bundle)
            }


        }

        return view
    }

}