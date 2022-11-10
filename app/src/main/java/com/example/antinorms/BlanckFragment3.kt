package com.example.antinorms

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.antinorms.models.createproject.createProject
import com.example.antinorms.models.createproject.createresp
import com.example.antinorms.models.projectResp.Profile


import com.example.antinorms.models.projectResp.ProjectResponse
import com.example.antinorms.models.teckstack.techresp
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_blank2.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class BlankFragment3 : Fragment(){

    private var rvData2: RecyclerView? = null
    private var progressBar2: ProgressBar? = null
    private var llParent2: LinearLayout? = null
    private var bottomSheetDialog: BottomSheetDialog? = null
    private var searchView1 : EditText? = null
    var filterdNames1 = mutableListOf<Profile>()
    var list1 = mutableListOf<Profile>()
    lateinit var myListAdapter1 : MyDataChildAdapter2
    var autoCompleteTextView: AutoCompleteTextView?=null
    var arr1: ArrayList<String> = ArrayList()
    var teckStack: ArrayList< techresp.Data?> = ArrayList()
    var selectedTeckStackId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_blank2, container, false)
        bottomSheetDialog = BottomSheetDialog(view.context, R.style.CustomBottomSheetDialogTheme)
        rvData2 = view.findViewById(R.id.recyclerView2)
        progressBar2 = view.findViewById(R.id.progresBar2)
        llParent2 = view.findViewById(R.id.llParent2)
        searchView1 = view.findViewById(R.id.et_search1)


        setSearch1()


        view.imageView2.setOnClickListener {
            bottomSheetDialog?.setContentView(R.layout.dialog2)
            bottomSheetDialog?.behavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED

            var projectname = bottomSheetDialog?.findViewById<EditText>(R.id.txt)
            var clientname = bottomSheetDialog?.findViewById<EditText>(R.id.txt1)
            var estimatedate = bottomSheetDialog?.findViewById<EditText>(R.id.txt2)
            var internalmeetingdaysandtiming = bottomSheetDialog?.findViewById<EditText>(R.id.txt3)
            var clientmeetingdaysandtiming = bottomSheetDialog?.findViewById<EditText>(R.id.txt5)
            var techstack = bottomSheetDialog?.findViewById<AutoCompleteTextView>(R.id.autoComplete)
            var prjecttype = bottomSheetDialog?.findViewById<EditText>(R.id.txt8)
            var status = bottomSheetDialog?.findViewById<EditText>(R.id.txt9)
            var demourl = bottomSheetDialog?.findViewById<EditText>(R.id.txt10)
            var clientcontactdetails = bottomSheetDialog?.findViewById<EditText>(R.id.txt11)



            techstack?.setOnClickListener {
                gettech()

            }




            var startdate = bottomSheetDialog?.findViewById<EditText>(R.id.txt14)
            var c1: Calendar = Calendar.getInstance();
            var year1 = c1.get(Calendar.YEAR);
            var month1 = c1.get(Calendar.MONTH);
            var day1 = c1.get(Calendar.DAY_OF_MONTH)
            startdate?.keyListener = null
            startdate?.setOnClickListener {

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
                        var i1 = i1
                        val date1 = i2.toString() + "/" + ++i1 + "/" + i
                        startdate.setText(date1)
                    }, year1, month1, day1
                )
                datePickerDialog.show()
            }

            var c: Calendar = Calendar.getInstance();
            var year = c.get(Calendar.YEAR);
            var month = c.get(Calendar.MONTH);
            var day = c.get(Calendar.DAY_OF_MONTH)
            estimatedate?.keyListener = null
            estimatedate?.setOnClickListener {

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
                        var i1 = i1
                        val date = i2.toString() + "/" + ++i1 + "/" + i
                        estimatedate.setText(date)
                    }, year, month, day
                )
                datePickerDialog.show()
            }

            bottomSheetDialog?.findViewById<Button>(R.id.add_project)?.setOnClickListener {

                val createProject = createProject(
                    projectName = projectname?.text.toString(),
                    clientName = clientname?.text.toString(),
                    estimatedEndDate = estimatedate?.text.toString(),
                    internalMeetingDaysAndTimings = internalmeetingdaysandtiming?.text.toString(),
                    clientMeetingDaysAndTimings = clientmeetingdaysandtiming?.text.toString(),
//                    techStack = techstack?.text.toString(),
                    techStack = selectedTeckStackId,
                    typeOfProject = prjecttype?.text.toString(),
                    status = status?.text.toString(),
                    demoUrls = demourl?.text.toString(),
                    clientPointOfContact = clientcontactdetails?.text.toString(),
                    startDate = startdate?.text.toString()

                )
                Log.d("BlankFragment3", "createProject Request: ${Gson().toJson(createProject)} ")
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
                    Toast.makeText(requireActivity(), response.body()?.message, Toast.LENGTH_SHORT)
                        .show()
                    bottomSheetDialog?.dismiss()
                    getMyData2()

                    Log.e("response", "${response.body()?.message}")

                } else {
                    Toast.makeText(
                        requireContext(),
                        "${response.body().toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<createresp?>, t: Throwable) {
                Log.d("response123", "onFailure: " + t.message)
                progressBar2?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

            }

        })


    }



    private fun gettech() {
        progressBar2?.visibility = View.VISIBLE

        val retrofitData =
            DashboardActivity.token?.let {

                RetrofitService.networkCall().gettech("Bearer $it")
            }


        retrofitData?.enqueue(object : Callback<techresp?> {
            override fun onResponse(
                call: Call<techresp?>,
                response: Response<techresp?>
            ) {
                Log.e("response", "hbuhbi ${call.request()} ${response.isSuccessful}")

                if (response.isSuccessful) {
                    progressBar2?.visibility = View.GONE
                    Log.e("response", "${response.body()?.data?.size}")

                    teckStack =  (response.body()?.data as ArrayList<techresp.Data?>?)!!
                    for(i in response.body()?.data!!){
                        arr1.add(i?.name!!)
                    }
                    var adapter = ArrayAdapter<String>(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arr1)
                    autoCompleteTextView = bottomSheetDialog?.findViewById<AutoCompleteTextView>(R.id.autoComplete)
                    autoCompleteTextView?.setAdapter(adapter)

                    autoCompleteTextView?.showDropDown()
                    autoCompleteTextView?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

                        selectedTeckStackId = teckStack.get(position)?._id ?: ""
                    }

                }

            }

            override fun onFailure(call: Call<techresp?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
                Log.e("response", "onfailre ${t.message}")
                progressBar2?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

            }

        })


    }

    private fun getMyData2() {
        list1.clear()

        rvData2?.visibility = View.GONE
        progressBar2?.visibility = View.VISIBLE
        val retrofitData =
            DashboardActivity.token?.let {
                RetrofitService.networkCall().getData2("Bearer $it")
            }

        retrofitData?.enqueue(object : Callback<ProjectResponse?> {
            override fun onResponse(
                call: Call<ProjectResponse?>,
                response: Response<ProjectResponse?>
            ) {
                rvData2?.visibility = View.VISIBLE
                progressBar2?.visibility = View.GONE

                if (response.isSuccessful) {
                    /*val adapter = response.body()?.let {it-> MyListAdapter(requireActivity(),it.data) }
                    rvData?.adapter = adapter
*/                 Log.d("Resp!", "onResponse: ${response.body()!!.profile.toMutableList()}")

                    setUpAdapter(response.body()?.profile)
                    list1 = response.body()!!.profile.toMutableList()
                    myListAdapter1.addData2(response.body()!!.profile.toMutableList())
                } else {
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


        val layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL, false
        )
        rvData2?.layoutManager = layoutManager
        myListAdapter1 = MyDataChildAdapter2(requireContext(), 0){
            getMyData2()
        }
        rvData2?.adapter = myListAdapter1

    }

    private fun setSearch1() {
        searchView1?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                search(s.toString())
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int
            ) {
            }
        })
    }

    @SuppressLint("DefaultLocale")
    private fun search(text: String) {

        filterdNames1 = mutableListOf()
        for (s in list1) {

            var temp = s.projectName
            if (temp.toLowerCase().contains(text.toLowerCase())) {
                filterdNames1.add(s)

            }
        }

        if (::myListAdapter1.isInitialized){
            myListAdapter1.addData2(filterdNames1)
        }
    }







}