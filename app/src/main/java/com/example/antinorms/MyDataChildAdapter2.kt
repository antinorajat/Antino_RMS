package com.example.antinorms


import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.antinorms.databinding.RowBinding
import com.example.antinorms.models.projectResp.Profile
import com.example.antinorms.models.updateProjectModel.req.UpdateData
import com.example.antinorms.models.updateProjectModel.req.res.UpdateProjectReq
import com.example.antinorms.models.updateProjectModel.res.UpdateProjecRes
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.nhaarman.supertooltips.ToolTipRelativeLayout
import retrofit2.Response


class MyDataChildAdapter2(val context: Context, val index: Int, var returnCallBack: () -> Unit) :
    RecyclerView.Adapter<MyDataChildAdapter2.ViewHolder>() {
    var list1: List<Profile> = mutableListOf()
    var arr: Array<String> = arrayOf()
    var list2: ArrayList<String> = arrayListOf()
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
            val techB = bottomSheetDialog2.findViewById<Spinner>(R.id.spinner)
            techB?.isEnabled = false
            list2.clear()
            myListData.techStack.forEach {
                list2.add(it.name)
            }
            var adapter = ArrayAdapter<String>(
                context,
                android.R.layout.simple_spinner_dropdown_item, list2
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            techB?.adapter = adapter
            Log.e("ListData", "$myListData")
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
            val projectmanagerB =
                bottomSheetDialog2.findViewById<EditText>(R.id.project_manager_tv2)
                projectmanagerB?.setText(myListData.projectManager?.name)
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
                val token = DashboardActivity.token
                val data = com.example.UpdateProjectReq(
                    clientMeetingDaysAndTimings = myListData.clientMeetingDaysAndTimings,
                    clientName = clientB?.text.toString(),
                    clientPointOfContact = myListData.clientPointOfContact,
                    demoUrls = myListData.demoUrls,
                    estimatedEndDate = estimatedateB?.text.toString(),
                    internalMeetingDaysAndTimings = internalB?.text.toString(),
                    projectName = myListData.projectName,
                    startDate = startdateB?.text.toString(),
                    status = myListData.status,
                    techStack = myListData.techStack.map { it.name },
                    typeOfProject = projectB?.text.toString()
                )
                Log.d("MyDataChildAdapter2", "$data")

                try {
                    retrofitData2.updateProjects(
                        "Bearer $token",
                        UpdateProjectReq(id = myListData._id,updateData = UpdateData(clientMeetingDaysAndTimings = myListData.clientMeetingDaysAndTimings,
                        clientName = clientB?.text.toString(), clientPointOfContact = clientcontactB?.text.toString(),
                        estimatedenddate = estimatedateB?.text.toString(),
                        internalMeetingDaysAndTimings = internalB?.text.toString(),
                        projectmanager = projectmanagerB?.text.toString(),
                        startdate = startdateB?.text.toString(),
                        techStack = myListData.techStack, typeOfProject = projectB?.text.toString()))

                    ).enqueue(object : retrofit2.Callback<UpdateProjecRes> {
                        override fun onResponse(
                            call: retrofit2.Call<UpdateProjecRes>,
                            response: Response<UpdateProjecRes>
                        ) {
                            Log.d(
                                "updateProjet::0",
                                "onResponse: ${Gson().toJson(response.body())} "
                            )

                            if (response.isSuccessful) {
                                Toast.makeText(context, "Data Saved", Toast.LENGTH_SHORT).show()
                                returnCallBack()
                                bottomSheetDialog2.dismiss()
                            } else {
                                Log.d("updateProjet::1", "onResponse: ${response.body()} ")

                                Log.d("ErrorR", "onResponse: ${response.message().toString()} ")
                                Toast.makeText(context, "Data Saved failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onFailure(
                            call: retrofit2.Call<UpdateProjecRes>,
                            t: Throwable
                        ) {
                            Log.d("updateProjet::2", "onResponse: ${t.message} ")
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
                statusB?.isEnabled = false
                projectB?.isEnabled = true
                clientB?.isEnabled = true
                meetingB?.isEnabled = true
                internalB?.isEnabled = true
                clientcontactB?.isEnabled = true
                startdateB?.isEnabled = true
                projectmanagerB?.isEnabled = true
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
                    if (myListData.developers.isNotEmpty()) {
                        holder.textView2.text =
                            "${myListData.developers.get(0).firstName + myListData.developers.get(0).lastName}"
                        holder.textView2.setOnClickListener {
                            val tooltip: SimpleTooltip = SimpleTooltip.Builder(context)
                                .anchorView(holder.textView2)
                                .text(temp)
                                .gravity(Gravity.TOP)
                                .animated(true)
                                .transparentOverlay(true)
                                .build()
                            tooltip.show()
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
    fun addData2(serviceList: MutableList<Profile>) {
        Log.e("listData1", "$serviceList")
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

