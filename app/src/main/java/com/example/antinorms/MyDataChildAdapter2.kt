package com.example.antinorms


import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.antinorms.databinding.RowBinding

import com.example.antinorms.models.projectResp.Profile
import com.example.antinorms.models.updateProjectModel.req.res.UpdateProjectReq
import com.example.antinorms.models.updateProjectModel.res.UpdateProjecRes
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nhaarman.supertooltips.ToolTipRelativeLayout
import retrofit2.Response


class MyDataChildAdapter2(val context: Context, val index: Int, ) :
    RecyclerView.Adapter<MyDataChildAdapter2.ViewHolder>() {

    var list1 : List<Profile> = mutableListOf()


    private val data = MutableLiveData<SaveDevRes>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {


        val binding = RowBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myListData = list1[position]



        holder.textView1.setPaintFlags(holder.textView1.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        holder.textView1.text = myListData.projectName
        if (myListData.developers.isEmpty()) {
            holder.textView2.text = " "
        } else {
            holder.textView2.text = myListData.developers[0].firstName
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
            projectB?.setText(myListData.typeOfProject)

            val clientB = bottomSheetDialog2.findViewById<EditText>(R.id.client_tv2)
            clientB?.setText(myListData.clientName)

            val meetingB = bottomSheetDialog2.findViewById<EditText>(R.id.meeting_tv2)
            meetingB?.setText(myListData.clientMeetingDaysAndTimings)

            Log.d("errorw", (myListData).clientMeetingDaysAndTimings)


            val internalB = bottomSheetDialog2.findViewById<EditText>(R.id.internal_meeting_tv2)
            internalB?.setText(myListData.internalMeetingDaysAndTimings)


            val clientcontactB =
                bottomSheetDialog2.findViewById<EditText>(R.id.client_contact_meeting_tv2)
            clientcontactB?.setText(myListData.clientPointOfContact)


            val startdateB =
                bottomSheetDialog2.findViewById<EditText>(R.id.start_date_tv2)
            startdateB?.setText(myListData.startDate)

            val estimatedateB =
                bottomSheetDialog2.findViewById<EditText>(R.id.estimate_end_date_tv2)
            estimatedateB?.setText(myListData.estimatedEndDate)

            val projectmanagerB = bottomSheetDialog2.findViewById<EditText>(R.id.project_manager_tv2)
            projectmanagerB?.setText(myListData.projectManager.name)




            val confirmButton2 = bottomSheetDialog2.findViewById<TextView>(R.id.confirm2)
            val editButton2 = bottomSheetDialog2.findViewById<TextView>(R.id.edit2)
            val retrofitData2 = RetrofitService.networkCall()

            confirmButton2?.setOnClickListener {
                editButton2?.isEnabled = true
                statusB?.isEnabled = false
                techB?.isEnabled = false
                projectB?.isEnabled = false
                clientB?.isEnabled = false
                clientcontactB?.isEnabled = false
                internalB?.isEnabled = false
                startdateB?.isEnabled = false
                projectmanagerB?.isEnabled = false
                estimatedateB?.isEnabled = false


                try {
                    retrofitData2.updateProjects( "Bearer ${DashboardActivity.token}",
                        updateProjectReq = UpdateProjectReq(
                            id = myListData._id,
                            updateData = com.example.antinorms.models.updateProjectModel.req.UpdateData(
                                techStack = myListData.techStack.map { it.name },
                                typeOfProject = myListData.projectName,
                                clientName = myListData.clientName,
                                clientMeetingDaysAndTimings  = myListData.clientMeetingDaysAndTimings,
                                internalMeetingDaysAndTimings = myListData.internalMeetingDaysAndTimings,
                                clientPointOfContact = myListData.clientPointOfContact,
                                projectmanager = myListData.projectManager.name,
                                estimatedenddate = myListData.estimatedEndDate,
                                startdate = myListData.startDate


                            )
                        )
                    )
                        .enqueue(object : retrofit2.Callback<UpdateProjecRes> {
                            override fun onResponse(
                                call: retrofit2.Call<UpdateProjecRes>,
                                response: Response<UpdateProjecRes>
                            ) {
                                if (response.isSuccessful) {
                                    Toast.makeText(context, "Data Saved", Toast.LENGTH_SHORT).show()
                                } else {
                                    Log.d("ErrorR", "onResponse: ${response.message().toString()} ")
                                    Toast.makeText(context, "Data Saved failed", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }

                            override fun onFailure(call: retrofit2.Call<UpdateProjecRes>, t: Throwable) {
                                Log.d("ErrorR", "onResponse: ${t.message} ")
                                Toast.makeText(context, "Some error occurred!", Toast.LENGTH_SHORT)
                                    .show()
                            }

                        })


                } catch (e: Exception) {
                    Toast.makeText(context, "Some error occurred! ${e.message}", Toast.LENGTH_SHORT)
                        .show()

                }
            }


            editButton2?.setOnClickListener {
                techB?.isEnabled = true
                statusB?.isEnabled = true
                projectB?.isEnabled = true
                clientB?.isEnabled = true
                meetingB?.isEnabled = true
                internalB?.isEnabled = true
                clientcontactB?.isEnabled = true
                startdateB?.isEnabled = true
                projectmanagerB?.isEnabled=true
                estimatedateB?.isEnabled = true
            }
            bottomSheetDialog2.show()
        }



        for (i in 0 until 3) {

            when (i) {
                0 -> {
                    holder.textView1.text = myListData.projectName

                }
                1 -> {
                    var temp = ""

                    if (myListData.developers.isNotEmpty()) {
                        for (j in 0 until myListData.developers.size) {
                            if (j == myListData.developers.size - 1) {
                                temp += "${myListData.developers[j].firstName} ${myListData.developers[j].lastName}"
                            } else {
                                temp += "${myListData.developers[j].firstName} ${myListData.developers[j].lastName}, "
                            }
                        }
                    }

                    //holder.textView2.text = temp
                    if(myListData.developers.isNotEmpty()) {
                        holder.textView2.text =
                            "${myListData.developers.get(0).firstName + myListData.developers.get(0).lastName}"


                        //holder.textView2.text = temp
                        holder.textView2.setOnClickListener {
                            /*onItemClickListener.onItemClick(position)


                        val toolTip = ToolTip()
                            .withText("A beautiful View")
                            .withColor(Color.RED)
                            .withShadow()

                        //.withAnimationType(ToolTip.ANIMATIONTYPE_FROMTOP)
                        var myToolTipView = holder.toolTipRelativeLayout.showToolTipForView(
                            toolTip,
                            holder.textView2
                        )
                        myToolTipView.setOnToolTipViewClickedListener(ToolTipView.OnToolTipViewClickedListener {
                            Toast.makeText(context, "Clicked on toolTip", Toast.LENGTH_SHORT).show()
                        })*/


                            val tooltip: SimpleTooltip = SimpleTooltip.Builder(context)
                                .anchorView(holder.textView2)
                                .text(temp)
                                .gravity(Gravity.TOP)
                                .animated(true)
                                .transparentOverlay(true)
                                .build()
                            tooltip.show()
                            //mHandler.postDelayed(Runnable { tooltip.dismiss() }, 3000)


                        }

                    }
                }
                2 -> {
                    holder.textView3.text = myListData.clientName
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list1.size
    }

    fun addData2(serviceList : MutableList<Profile>) {
        list1 = serviceList
        notifyDataSetChanged()
    }




    inner class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {

        var textView1: TextView = binding.txt1
        var textView2: TextView = binding.txt2
        var textView3: TextView = binding.txt3
        var toolTipRelativeLayout: ToolTipRelativeLayout = binding.activityMainTooltipRelativeLayout


    }


}
