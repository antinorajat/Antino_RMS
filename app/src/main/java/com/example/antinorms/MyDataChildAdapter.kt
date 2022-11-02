package com.example.antinorms

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.antinorms.databinding.RowBinding
import com.example.antinorms.models.getdesignation.getdesignationresp
import com.example.antinorms.models.groupdata
import com.example.antinorms.models.role
import com.example.antinorms.models.teckstack.techresp
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import retrofit2.Response


class MyDataChildAdapter(val context: Context, val index: Int,var returnCallBack:()->Unit) :
    RecyclerView.Adapter<MyDataChildAdapter.ViewHolder>() {
    var roleId=""
    var designatiion=""
    var group=""
    var tech=""
    var list: List<Data> = mutableListOf()
    var list2: ArrayList<String> = arrayListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {


        val binding = RowBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myListData = list[position]
        Log.d("dfxxfgfgfg", "${myListData.isAvailable} ${myListData.joiningDate}")

        holder.textView1.setPaintFlags(holder.textView1.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

        holder.textView1.text = myListData.empId.toString()
        holder.textView2.text =
            myListData.firstName.toString() + " " + myListData.lastName.toString()
        if (myListData.isAvailable != null)
            holder.textView3.text = myListData.isAvailable.toString()
        else
            holder.textView3.text = "null"



        holder.textView1.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme)
            bottomSheetDialog.setContentView(R.layout.bottomsheet)
            bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED


            val emailB = bottomSheetDialog.findViewById<EditText>(R.id.email)
            emailB?.setText(myListData.email)
            val seniority = bottomSheetDialog.findViewById<EditText>(R.id.seniority_tv2)
            seniority?.setText(myListData.seniority)
            val remarks = bottomSheetDialog.findViewById<EditText>(R.id.remark_tv2)
            remarks?.setText(myListData.remarks)
            val phoneNo = bottomSheetDialog.findViewById<EditText>(R.id.phone_tv2)
            phoneNo?.setText(myListData.phoneNumber)
            val emergency_no = bottomSheetDialog.findViewById<EditText>(R.id.Emergency_tv2)
            emergency_no?.setText(myListData.emergencyContactNumber)
            val experience = bottomSheetDialog.findViewById<EditText>(R.id.workExp_tv2)
            experience?.setText(myListData.workingExperienceInMonths.toString())
            val mangerB = bottomSheetDialog.findViewById<EditText>(R.id.manager_tv2)
            mangerB?.setText(myListData.reportingPm?.name ?: "")
            val joiningB = bottomSheetDialog.findViewById<EditText>(R.id.joining_tv2)
            joiningB?.setText(myListData.joiningDate.toString().split("T")[0])

            val roleB = bottomSheetDialog.findViewById<AutoCompleteTextView>(R.id.role_tv2)
            roleB?.setText(myListData.Role?.name)
            roleB?.setOnClickListener {
//
//                val adapter: ArrayAdapter<String> = ArrayAdapter<String>(holder.textView1.context, android.R.layout.select_dialog_item, listOf("A","B"))
//                roleB.threshold = 1 //will start working from first character
//
//                roleB.setAdapter(adapter) //setting the adapter data into the AutoCompleteTextView
//                roleB.showDropDown()

                try {
                    val retrofitData = DashboardActivity.token?.let {
                        RetrofitService.networkCall().getrole("Bearer $it")
                    }
                    retrofitData?.enqueue(object : retrofit2.Callback<role> {
                        override fun onResponse(
                            call: retrofit2.Call<role>,
                            response: Response<role>
                        ) {
                            Log.d("MyDataChildAdapter", "Response Code: ${response.code()}")
                            Log.d("MyDataChildAdapter", "Response Body:${response.body()}")
                            if (response.isSuccessful) {

                                val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                                    holder.textView1.context,
                                    android.R.layout.select_dialog_item,
                                    response.body()?.data?.map { it.name } ?: listOf()
                                )
                                roleB.setAdapter(adapter) //setting the adapter data into the AutoCompleteTextView
                                roleB.showDropDown()

                                roleB.onItemClickListener =
                                    OnItemClickListener { parent, arg1, pos, id ->
                                         roleId = response.body()?.data?.get(pos)?._id ?:""
                                    }

                            } else {
                                Log.d("ErrorR", "onResponse: ${response.message().toString()} ")
                                Toast.makeText(context, "Data Saved failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onFailure(call: retrofit2.Call<role>, t: Throwable) {
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


            val designationB = bottomSheetDialog.findViewById<AutoCompleteTextView>(R.id.designation_tv2)
            designationB?.setText(myListData.designation?.name)
            designationB?.setOnClickListener {

                try {
                    val retrofitData = DashboardActivity.token?.let {
                        RetrofitService.networkCall().getdesignation("Bearer $it")
                    }
                    retrofitData?.enqueue(object : retrofit2.Callback<getdesignationresp> {
                        override fun onResponse(
                            call: retrofit2.Call<getdesignationresp>,
                            response: Response<getdesignationresp>
                        ) {
                            Log.d("MyDataChildAdapter", "Response Code: ${response.code()}")
                            Log.d("MyDataChildAdapter", "Response Body:${response.body()}")
                            if (response.isSuccessful) {

                                val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                                    holder.textView1.context,
                                    android.R.layout.select_dialog_item,
                                    response.body()?.data?.map { it?.name } ?: listOf()
                                )
                                designationB.setAdapter(adapter) //setting the adapter data into the AutoCompleteTextView
                                designationB.showDropDown()

                                designationB.onItemClickListener =
                                    OnItemClickListener { parent, arg1, pos, id ->
                                        designatiion = response.body()?.data?.get(pos)?._id ?:""
                                    }

                            } else {
                                Log.d("ErrorR", "onResponse: ${response.message().toString()} ")
                                Toast.makeText(context, "Data Saved failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onFailure(call: retrofit2.Call<getdesignationresp>, t: Throwable) {
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



            val techB = bottomSheetDialog.findViewById<AutoCompleteTextView>(R.id.tech_tv2)
            techB?.setText(myListData.techStack?.name)

            techB?.setOnClickListener {

                try {
                    val retrofitData = DashboardActivity.token?.let {
                        RetrofitService.networkCall().gettech("Bearer $it")
                    }
                    retrofitData?.enqueue(object : retrofit2.Callback<techresp> {
                        override fun onResponse(
                            call: retrofit2.Call<techresp>,
                            response: Response<techresp>
                        ) {
                            Log.d("MyDataChildAdapter", "Response Code: ${response.code()}")
                            Log.d("MyDataChildAdapter", "Response Body:${response.body()}")
                            if (response.isSuccessful) {

                                val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                                    holder.textView1.context,
                                    android.R.layout.select_dialog_item,
                                    response.body()?.data?.map { it?.name} ?: listOf()
                                )
                                techB.setAdapter(adapter) //setting the adapter data into the AutoCompleteTextView
                                techB.showDropDown()

                                techB.onItemClickListener =
                                    OnItemClickListener { parent, arg1, pos, id ->
                                        tech= response.body()?.data?.get(pos)?._id ?:""
                                    }

                            } else {
                                Log.d("ErrorR", "onResponse: ${response.message().toString()} ")
                                Toast.makeText(context, "Data Saved failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onFailure(call: retrofit2.Call<techresp>, t: Throwable) {
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



            val groupB = bottomSheetDialog.findViewById<AutoCompleteTextView>(R.id.group_tv2)
            groupB?.setText(myListData.group?.name ?: "null")

            groupB?.setOnClickListener {

                try {
                    val retrofitData = DashboardActivity.token?.let {
                        RetrofitService.networkCall().getgroup("Bearer $it")
                    }
                    retrofitData?.enqueue(object : retrofit2.Callback<groupdata> {
                        override fun onResponse(
                            call: retrofit2.Call<groupdata>,
                            response: Response<groupdata>
                        ) {
                            Log.d("MyDataChildAdapter", "Response Code: ${response.code()}")
                            Log.d("MyDataChildAdapter", "Response Body:${response.body()}")
                            if (response.isSuccessful) {

                                val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                                    holder.textView1.context,
                                    android.R.layout.select_dialog_item,
                                    response.body()?.data?.map { it?.group_name} ?: listOf()
                                )
                                groupB.setAdapter(adapter) //setting the adapter data into the AutoCompleteTextView
                                groupB.showDropDown()

                                groupB.onItemClickListener =
                                    OnItemClickListener { parent, arg1, pos, id ->
                                       group= response.body()?.data?.get(pos)?._id ?:""
                                    }

                            } else {
                                Log.d("ErrorR", "onResponse: ${response.message().toString()} ")
                                Toast.makeText(context, "Data Saved failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onFailure(call: retrofit2.Call<groupdata>, t: Throwable) {
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






            val projectB = bottomSheetDialog.findViewById<EditText>(R.id.project_tv2)
            projectB?.setText(myListData.projects.toString())


            val billableB = bottomSheetDialog.findViewById<EditText>(R.id.billable_tv3)
            billableB?.setText(myListData.billable.toString())


            val isfresherB = bottomSheetDialog.findViewById<EditText>(R.id.isfresher_tv2)
            isfresherB?.setText(myListData.isFresher.toString())

            val confirmButton = bottomSheetDialog.findViewById<TextView>(R.id.confirm)
            val editButton = bottomSheetDialog.findViewById<TextView>(R.id.edit)
            val retrofitData = RetrofitService.networkCall()

            confirmButton?.setOnClickListener {
                editButton?.isEnabled = true
                emailB?.isEnabled = false
                seniority?.isEnabled = true
                remarks?.isEnabled = false
                phoneNo?.isEnabled = false
                emergency_no?.isEnabled = false
                experience?.isEnabled = false
                mangerB?.isEnabled = false
                joiningB?.isEnabled = false
                designationB?.isEnabled = false
                roleB?.isEnabled = false
                techB?.isEnabled = true
                groupB?.isEnabled = false
                projectB?.isEnabled = false
                billableB?.isEnabled = false
                isfresherB?.isEnabled = false


                val saveDevrequest = SaveDevrequest(
                    firstName = myListData.firstName.toString(),
                    lastName = myListData.lastName.toString(),
                    email = emailB?.text.toString(),
                    phoneNumber = phoneNo?.text.toString(),
                    empId = myListData.empId,
                    emergencyContactNumber = emergency_no?.text.toString(),
                    workingExperienceInMonths = experience?.text.toString().let { it.toIntOrNull() ?:0 },
                    role = roleId,
                    techStack = tech,
                    joiningDate = myListData.joiningDate,
                    designation = designatiion,
                    remarks = remarks?.text.toString()
//               reportingPm =  myListData.reportingPm?.name,
//               project = myListData.projects?.toString(),
//               billable = myListData.billable?.toString(),
//               isFresher = myListData.isFresher

                )
                Log.d("MyDataChildAdapter", "DevId ${myListData.Id}")
                Log.d(
                    "MyDataChildAdapter",
                    "Update Developer RequestBody ${Gson().toJson(saveDevrequest)}"
                )
                try {
                    val retrofitData =
                        DashboardActivity.token?.let {
                            RetrofitService.networkCall().updateDeveloper(
                                "Bearer $it", devId = myListData.Id!!,
                                saveDevrequest
                            )


                        }
                    retrofitData?.enqueue(object : retrofit2.Callback<SaveDevRes> {
                        override fun onResponse(
                            call: retrofit2.Call<SaveDevRes>,
                            response: Response<SaveDevRes>
                        ) {
                            Log.d("MyDataChildAdapter", "Response Code: ${response.code()}")
                            Log.d("MyDataChildAdapter", "Response Body:${response.body()}")
                            if (response.isSuccessful) {
                                Toast.makeText(context, "Data Saved", Toast.LENGTH_SHORT).show()
                                returnCallBack()
                                bottomSheetDialog.dismiss()
                            } else {
                                Log.d("ErrorR", "onResponse: ${response.message().toString()} ")
                                Toast.makeText(context, "Data Saved failed", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onFailure(call: retrofit2.Call<SaveDevRes>, t: Throwable) {
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

            editButton?.setOnClickListener {

                editButton?.isEnabled = false
                emailB?.isEnabled = true
                seniority?.isEnabled = false
                remarks?.isEnabled = true
                phoneNo?.isEnabled = true
                emergency_no?.isEnabled = true
                experience?.isEnabled = true
                mangerB?.isEnabled = false
                joiningB?.isEnabled = true
                designationB?.isEnabled = true
                roleB?.isEnabled = true
                techB?.isEnabled = true
                groupB?.isEnabled = true
                projectB?.isEnabled = false
                billableB?.isEnabled = false
                isfresherB?.isEnabled = false


            }

            bottomSheetDialog.show()
        }


        when (index) {
            0 -> {
                holder.textView1.text = myListData.empId.toString()

            }
            1 -> {
                holder.textView1.text = myListData.firstName + " " + myListData.lastName

            }
            2 -> {


                holder.textView1.text = myListData.isAvailable.toString()

            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun addData(serviceList: MutableList<Data>) {
        list = serviceList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {

        var textView1: TextView = binding.txt1
        var textView2: TextView = binding.txt2
        var textView3: TextView = binding.txt3


    }


}
