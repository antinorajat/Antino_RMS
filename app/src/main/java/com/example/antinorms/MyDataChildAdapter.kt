package com.example.antinorms

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.antinorms.databinding.RowBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import retrofit2.Response
import java.lang.Exception


class MyDataChildAdapter(val context: Context, val index :Int) :
    RecyclerView.Adapter<MyDataChildAdapter.ViewHolder>() {

    var list : List<Data> = mutableListOf()
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

        holder.textView1.text = myListData.empId.toString()
        holder.textView2.text = myListData.firstName.toString()+" "+myListData.lastName.toString()
        holder.textView3.text = myListData.isAvailable.toString()

        if (myListData.isAvailable == null){
            holder.textView3.setText("N/A")
        }

        holder.textView1.setOnClickListener{
            val bottomSheetDialog = BottomSheetDialog(context,R.style.CustomBottomSheetDialogTheme)
            bottomSheetDialog.setContentView(R.layout.bottomsheet)
            bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED


            val emailB=bottomSheetDialog.findViewById<EditText>(R.id.email)
            emailB?.setText(myListData.email)
            val seniority=bottomSheetDialog.findViewById<EditText>(R.id.seniority_tv2)
            seniority?.setText(myListData.seniority)
            val remarks=bottomSheetDialog.findViewById<EditText>(R.id.remark_tv2)
            remarks?.setText(myListData.remarks)
            val phoneNo=bottomSheetDialog.findViewById<EditText>(R.id.phone_tv2)
            phoneNo?.setText(myListData.phoneNumber)
            val emergency_no=bottomSheetDialog.findViewById<EditText>(R.id.Emergency_tv2)
            emergency_no?.setText(myListData.emergencyContactNumber)
            val experience=bottomSheetDialog.findViewById<EditText>(R.id.workExp_tv2)
            experience?.setText(myListData.workingExperienceInMonths.toString())
            val mangerB = bottomSheetDialog.findViewById<EditText>(R.id.manager_tv2)
            mangerB?.setText(myListData.reportingPm?.name?: "")
            val joiningB = bottomSheetDialog.findViewById<EditText>(R.id.joining_tv2)
            joiningB?.setText(myListData.joiningDate.toString().split("T")[0])

            val roleB=bottomSheetDialog.findViewById<EditText>(R.id.role_tv2)
            roleB?.setText(myListData.Role?.name)

            val designationB=bottomSheetDialog.findViewById<EditText>(R.id.designation_tv2)
            designationB?.setText(myListData.designation?.name)

            val techB=bottomSheetDialog.findViewById<EditText>(R.id.tech_tv2)
            techB?.setText(myListData.techStack?.name ?: "")

            val groupB=bottomSheetDialog.findViewById<EditText>(R.id.group_tv2)
            groupB?.setText(myListData.group?.name)

            val projectB=bottomSheetDialog.findViewById<EditText>(R.id.project_tv2)
            projectB?.setText(myListData.projects.toString())


//
//            val billableB=bottomSheetDialog.findViewById<EditText>(R.id.billable_tv12)
//            billableB?.setText(myListData.)


            val confirmButton = bottomSheetDialog.findViewById<TextView>(R.id.confirm)
            val editButton = bottomSheetDialog.findViewById<TextView>(R.id.edit)
            val retrofitData = RetrofitService.networkCall()

            confirmButton?.setOnClickListener {
                editButton?.isEnabled = true
                emailB?.isEnabled = false
                seniority?.isEnabled = false
                remarks?.isEnabled = false
                phoneNo?.isEnabled = false
                emergency_no?.isEnabled = false
                experience?.isEnabled = false
                mangerB?.isEnabled = false
                joiningB?.isEnabled = false
                designationB?.isEnabled = false
                roleB?.isEnabled = false
                techB?.isEnabled = false
                 groupB?.isEnabled=false
                projectB?.isEnabled = false




           val saveDevrequest = SaveDevrequest(firstName = myListData.firstName.toString(),
               lastName = myListData.lastName.toString(),
               email = emailB?.text.toString(),
               phoneNumber = phoneNo?.text.toString(),
               empId = myListData.empId,
               emergencyContactNumber = emergency_no?.text.toString(),
               workingExperienceInMonths = experience?.text.toString().toInt(),
               role = roleB?.text.toString(),
               techStack = "",
               joiningDate = myListData.joiningDate,
               designation = designationB?.text.toString(),
              remarks = "",
               reportingPm =  myListData.reportingPm?.name,
               project = myListData.projects?.toString()
           )
                Log.d("error", Gson().toJson(saveDevrequest))
                try {
                    val retrofitData =
                        DashboardActivity.token?.let {
                            RetrofitService.networkCall().updateDeveloper("Bearer $it",devId = myListData.Id!!,
                                saveDevrequest)
                        }
                    retrofitData?.enqueue(object : retrofit2.Callback<SaveDevRes> {
                            override fun onResponse(
                                call: retrofit2.Call<SaveDevRes>,
                                response: Response<SaveDevRes>
                            ) {
                                if (response.isSuccessful) {
                                    Toast.makeText(context, "Data Saved", Toast.LENGTH_SHORT).show()
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

            editButton?.setOnClickListener{

                editButton?.isEnabled = false
                emailB?.isEnabled = true
                seniority?.isEnabled = true
                remarks?.isEnabled = true
                phoneNo?.isEnabled = true
                emergency_no?.isEnabled = true
                experience?.isEnabled = true
                mangerB?.isEnabled = true
                joiningB?.isEnabled = true
                designationB?.isEnabled=true
                roleB?.isEnabled = true
                techB?.isEnabled = true
                groupB?.isEnabled=true
                projectB?.isEnabled = true

            }

            bottomSheetDialog.show()
        }


        when(index){
            0 ->{
                holder.textView1.text = myListData.empId.toString()

            }
            1 ->{
                holder.textView1.text = myListData.firstName+" "+myListData.lastName

            }
            2 ->{




            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }




    fun addData(serviceList : MutableList<Data>) {
        list = serviceList
        notifyDataSetChanged()
    }

    inner class ViewHolder( val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {

        var textView1: TextView = binding.txt1
        var textView2: TextView = binding.txt2
        var textView3: TextView = binding.txt3


    }


}
