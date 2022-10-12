package com.example.antinorms

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.antinorms.listeners.OnItemClickListener
import com.example.antinorms.models.Register.registerdata
import com.example.antinorms.models.createproject.createProject
import com.example.antinorms.models.createproject.createresp
import com.example.antinorms.models.projectResp.Profile


import com.example.antinorms.models.projectResp.ProjectResponse
import com.example.antinorms.models.registerresp
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_blank.view.*
import kotlinx.android.synthetic.main.fragment_blank2.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class BlankFragment3 : Fragment(), OnItemClickListener {

    private var rvData2: RecyclerView?= null
    private var progressBar2: ProgressBar?= null
    private var llParent2: LinearLayout?= null
    private var bottomSheetDialog2 : BottomSheetDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_blank2, container, false)
        rvData2 = view.findViewById(R.id.recyclerView2)
        progressBar2 = view.findViewById(R.id.progresBar2)
        llParent2 = view.findViewById(R.id.llParent2)


        view.imageView2.setOnClickListener{
            val bottomSheetDialog = context?.let { it1 -> BottomSheetDialog(it1,R.style.CustomBottomSheetDialogTheme) }
            bottomSheetDialog?.setContentView(R.layout.dialog2)
            bottomSheetDialog?.behavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED

            var projectname = bottomSheetDialog?.findViewById<EditText>(R.id.txt)
            var clientname = bottomSheetDialog?.findViewById<EditText>(R.id.txt1)
            var estimatedate = bottomSheetDialog?.findViewById<EditText>(R.id.txt2)
            var internalmeetingdaysandtiming = bottomSheetDialog?.findViewById<EditText>(R.id.txt3)

            var clientmeetingdaysandtiming = bottomSheetDialog?.findViewById<EditText>(R.id.txt5)

            var techstack = bottomSheetDialog?.findViewById<EditText>(R.id.txt7)
            var prjecttype = bottomSheetDialog?.findViewById<EditText>(R.id.txt8)
            var status = bottomSheetDialog?.findViewById<EditText>(R.id.txt9)
            var demourl = bottomSheetDialog?.findViewById<EditText>(R.id.txt10)
            var clientcontactdetails = bottomSheetDialog?.findViewById<EditText>(R.id.txt11)

            var  c: Calendar = Calendar.getInstance();
            var year = c.get(Calendar.YEAR);
            var month = c.get(Calendar.MONTH);
            var day = c.get(Calendar.DAY_OF_MONTH)
            estimatedate?.keyListener=null
            estimatedate?.setOnClickListener {

                val datePickerDialog = DatePickerDialog(requireContext(),
                    DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
                        var i1 = i1
                        val date = i2.toString() + "/" + ++i1 + "/" + i
                        estimatedate.setText(date)
                    }, year, month, day
                )
                datePickerDialog.show()}

            bottomSheetDialog2?.findViewById<Button>(R.id.add_project)?.setOnClickListener {
                val createProject = createProject(
                    projectName = projectname?.text.toString(),
                    clientName = clientname?.text.toString(),
                    estimatedEndDate = estimatedate?.text.toString(),
                    internalMeetingDaysAndTimings = internalmeetingdaysandtiming?.text.toString(),
                    clientMeetingDaysAndTimings = clientmeetingdaysandtiming?.text.toString(),
                    techStack = techstack?.text.toString(),
                    typeOfProject = prjecttype?.text.toString(),
                    status = status?.text.toString(),
                    demoUrls = demourl?.text.toString(),
                    clientPointOfContact = clientcontactdetails?.text.toString()

                )
                getData4(createProject)
            }




            bottomSheetDialog?.show()




    }

        getMyData2()

        return view
    }

    private fun getData4(createProject: createProject) {
        rvData2?.visibility = View.GONE
        progressBar2?.visibility = View.VISIBLE
        Log.d("response123", Gson().toJson(createProject))
        val retrofitData =
            DashboardActivity.token?.let {
                RetrofitService.networkCall().getData4("Bearer $it", createProject)
            }

        retrofitData?.enqueue(object : Callback<createresp?> {
            override fun onResponse(
                call: Call<createresp?>,
                response: Response<createresp?>
            ) {
                Log.e("response123", "${response.body()?.message}")

                if (response.isSuccessful) {
                    /*val adapter = response.body()?.let {it-> MyListAdapter(requireActivity(),it.data) }
                    rvData?.adapter = adapter
*/
                    Toast.makeText(requireActivity(), response.body()?.message, Toast.LENGTH_SHORT).show()
                    bottomSheetDialog2?.dismiss()
                    getMyData2()

                    Log.e("response", "${response.body()?.message}")

                } else {
                    Toast.makeText(requireContext(), "${response.body().toString()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<createresp?>, t: Throwable) {
                Log.d("response123", "onFailure: " + t.message)
                progressBar2?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

            }

        })


    }




















    private fun getMyData2() {

        rvData2?.visibility = View.GONE
        progressBar2?.visibility = View.VISIBLE
        val retrofitData =
            DashboardActivity.token?.let {
                RetrofitService.networkCall().getData2("Bearer $it") }
        retrofitData?.enqueue(object : Callback<ProjectResponse?> {
            override fun onResponse(
                call: Call<ProjectResponse?>,
                response: Response<ProjectResponse?>
            ) {
                rvData2?.visibility = View.VISIBLE
                progressBar2?.visibility = View.GONE

                if(response.isSuccessful){
                    /*val adapter = response.body()?.let {it-> MyListAdapter(requireActivity(),it.data) }
                    rvData?.adapter = adapter
*/                 Log.d("DATA!", "onResponse: ${response.message()}")
                    setUpAdapter(response.body()?.profile)
                }else{
                    Log.d("DATA!", "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ProjectResponse?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
                progressBar2?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

            }

        })


    }

    private fun setUpAdapter(list: List<Profile>?) {
        val headerList = mutableListOf<String>()

        headerList.add("Project name ")
        headerList.add("Developers")
        headerList.add("Client name")


        val layoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.VERTICAL,false)
        rvData2?.layoutManager = layoutManager
        val myListAdapter = list?.let { MyDataChildAdapter2(requireContext(), it,0, this) }
        rvData2?.adapter = myListAdapter

    }

    override fun onItemClick(position: Int) {


    }


}