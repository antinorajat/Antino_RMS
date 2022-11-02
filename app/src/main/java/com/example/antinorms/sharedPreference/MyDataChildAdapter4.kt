package com.example.antinorms.sharedPreference

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.antinorms.MyDataChildAdapter3
import com.example.antinorms.databinding.Row3Binding
import com.example.antinorms.databinding.Row4Binding
import com.example.antinorms.models.createteam.response_teams
import com.example.antinorms.models.group_res

class MyDataChildAdapter4(val context: Context) :
    RecyclerView.Adapter<MyDataChildAdapter4.ViewHolder>() {

    var list : MutableList<group_res.Data> = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {


        val binding3 = Row4Binding.inflate(LayoutInflater.from(context),parent, false)
        return ViewHolder(binding3)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myListData1 = list?.get(position)
//        Log.d(
//            "kfmjkdm", "${ myListData1?.toString()}" )

        holder.textView1.setPaintFlags(holder.textView1.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        holder.textView1.text = myListData1?.group_name.toString()
        holder.textView2.text = myListData1?.mentor?.name
        holder.textView3.text = myListData1?.Developers?.size.toString()



    }

    override fun getItemCount(): Int {

        return list!!.size
    }

    fun addData(serviceList : MutableList<group_res.Data>) {
        list.clear()
        list?.addAll(serviceList)
        notifyDataSetChanged()
    }


//    fun addTeam2(serviceList1 : List<teams?>?) {
//        list1= serviceList1
//        notifyDataSetChanged()
//    }





    inner class ViewHolder( val binding3: Row4Binding) : RecyclerView.ViewHolder(binding3.root) {

        var textView1: TextView = binding3.txt7
        var textView2: TextView = binding3.txt8
        var textView3: TextView = binding3.txt9

    }


}

