package com.example.antinorms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.antinorms.databinding.ActivityMainBinding.inflate
//import com.example.antinorms.databinding.MyListAdapterBinding
//import com.example.antinorms.databinding.RowBinding


//class MyListAdapter(val context: Context, private val list : List<Data>,val headerList : MutableList<String>) :
//    RecyclerView.Adapter<MyListAdapter.ViewHolder>() {
//
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ViewHolder {
//
//        val binding = MyListAdapter.inflate(LayoutInflater.from(context),parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.textView1.text = headerList[position]
//        val layoutManager = LinearLayoutManager(context,
//            LinearLayoutManager.VERTICAL,false)
//        holder.recyclerView.layoutManager = layoutManager
//        val myListAdapter = MyDataChildAdapter(context,list,position)
//        holder.recyclerView.adapter = myListAdapter
//        holder.recyclerView.isNestedScrollingEnabled=false
//        holder.recyclerView.suppressLayout(true)
//
//    }
//
//    override fun getItemCount(): Int {
//        return headerList.size
//    }
//
//    inner class ViewHolder(binding: MyListAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
//
//        var textView1: TextView = binding.txt1
//        var recyclerView = binding.recyclerView
//
//
//
//
//
//
//
//
//
//
//    }
//
//
//}
