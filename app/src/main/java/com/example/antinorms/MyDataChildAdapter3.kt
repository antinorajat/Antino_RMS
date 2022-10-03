package com.example.antinorms

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.antinorms.databinding.RowBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog


class MyDataChildAdapter3(val context: Context, private val list : List<Profile>, val index :Int) :
    RecyclerView.Adapter<MyDataChildAdapter3.ViewHolder>() {


    private val data  =  MutableLiveData<SaveDevRes>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {


        val binding = RowBinding.inflate(LayoutInflater.from(context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myListData = list[position]



        holder.textView1.setPaintFlags(holder.textView1.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        holder.textView1.text = myListData.projectName
        if (myListData.developers.isEmpty()){
            holder.textView2.text = " "
        }else{
            holder.textView2.text = myListData.developers[0]
        }
        holder.textView3.text = myListData.clientName

        holder.textView1.setOnClickListener {
            val bottomSheetDialog2 =
                BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme)
            bottomSheetDialog2.setContentView(R.layout.bottomsheet2)
            bottomSheetDialog2.behavior.state = BottomSheetBehavior.STATE_EXPANDED
            val statusB = bottomSheetDialog2.findViewById<EditText>(R.id.status)
            statusB?.setText(myListData.status)

            val techB = bottomSheetDialog2.findViewById<EditText>(R.id.tech_tv3)
            techB?.setText(myListData.techStack[0].name)

            val projectB = bottomSheetDialog2.findViewById<EditText>(R.id.project_tv2)
            projectB?.setText(myListData.projectName)

            val clientB = bottomSheetDialog2.findViewById<EditText>(R.id.client_tv2)
            clientB?.setText(myListData.clientName)

            val meetingB = bottomSheetDialog2.findViewById<EditText>(R.id.meeting_tv2)
            meetingB?.setText(myListData.clientMeetingDaysAndTimings)

            val internalB = bottomSheetDialog2.findViewById<EditText>(R.id.internal_meeting_tv2)
            internalB?.setText(myListData.internalMeetingDaysAndTimings)


            val clientcontactB =
                bottomSheetDialog2.findViewById<EditText>(R.id.client_contact_meeting_tv2)
            clientcontactB?.setText(myListData.clientPointOfContact)


            val confirmButton2 = bottomSheetDialog2.findViewById<TextView>(R.id.confirm2)
            val editButton2 = bottomSheetDialog2.findViewById<TextView>(R.id.edit2)
            val retrofitData2 = RetrofitService.networkCall()

            editButton2?.setOnClickListener {
                editButton2?.isEnabled = true
                techB?.isEnabled = false
                statusB?.isEnabled = false
                projectB?.isEnabled = false
                clientB?.isEnabled = false
                meetingB?.isEnabled = false
                internalB?.isEnabled = false
                clientcontactB?.isEnabled = false

            }
            bottomSheetDialog2.show()
        }





        when(index){
            0 ->{
                holder.textView1.text = myListData.projectName

            }
            1 ->{
                holder.textView1.text = " "

            }
            2 ->{
                holder.textView1.text = myListData.clientName
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder( val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {

        var textView1: TextView = binding.txt1
        var textView2: TextView = binding.txt2
        var textView3: TextView = binding.txt3


    }


}
