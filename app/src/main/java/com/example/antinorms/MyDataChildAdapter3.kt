package com.example.antinorms

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.antinorms.databinding.Row3Binding
import com.example.antinorms.databinding.RowBinding
import com.example.antinorms.models.createteam.Data3
import com.example.antinorms.models.createteam.response_teams
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import retrofit2.Response
import java.lang.Exception


class MyDataChildAdapter3(val context: Context, val index :Int) :
    RecyclerView.Adapter<MyDataChildAdapter3.ViewHolder>() {

    var list : List<response_teams.Data?>? = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {


        val binding2 = Row3Binding.inflate(LayoutInflater.from(context),parent, false)
        return ViewHolder(binding2)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myListData1 = list?.get(position)
        Log.d(
            "kfmjkdm", "${ myListData1?._id.toString()}" )

        holder.textView1.setPaintFlags(holder.textView1.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        holder.textView1.text = myListData1?.name.toString()
        holder.textView2.text = myListData1?.email.toString()
        holder.textView3.text = myListData1?.designation?.name.toString()

/*
        when(index){
            0 ->{
                holder.textView1.text = myListData1?._id.toString()

            }
            1 ->{
                holder.textView1.text = myListData1?.name

            }
            2 ->{

                holder.textView1.text = myListData1?.email
            }

        }
*/
    }

    override fun getItemCount(): Int {

        return list!!.size
    }

    fun addData(serviceList : List<response_teams.Data?>?) {
        list = serviceList
        notifyDataSetChanged()
    }

    inner class ViewHolder( val binding2: Row3Binding) : RecyclerView.ViewHolder(binding2.root) {

        var textView1: TextView = binding2.txt6
        var textView2: TextView = binding2.txt7
        var textView3: TextView = binding2.txt8

    }


}
