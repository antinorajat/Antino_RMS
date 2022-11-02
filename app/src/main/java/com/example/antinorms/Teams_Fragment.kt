package com.example.antinorms

import android.annotation.SuppressLint
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
import com.example.antinorms.models.createteam.*
import com.example.antinorms.models.getdesignation.getdesignationresp
import com.example.antinorms.models.teckstack.techresp
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.dialog3.*
import kotlinx.android.synthetic.main.fragment_teams_.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Teams_Fragment : Fragment() {
    private var rvData: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var llParent: LinearLayout? = null
    private var bottomSheetDialog: BottomSheetDialog? = null
    private var searchView: EditText? = null
    var filterdNames = mutableListOf<response_teams.Data?>()
    var list = mutableListOf<response_teams.Data?>()
    var list1: List<teams?>? = arrayListOf()
    var list2: List<getdesignationresp?>? = arrayListOf()
    lateinit var myListAdapter3: MyDataChildAdapter3
    lateinit var AddTeamRequest:AddTeamRequest
   private var spin : Spinner? = null
    lateinit var autoCompleteTextView: AutoCompleteTextView
    lateinit var arr: ArrayList<String>
    var teckStack: java.util.ArrayList<techresp.Data?> = java.util.ArrayList()
    var selectedTeckStackId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_teams_, container, false)
        rvData = view.findViewById(R.id.recyclerView3)
        progressBar = view.findViewById(R.id.progresBar3)
        llParent = view.findViewById(R.id.llParent3)
        searchView = view.findViewById(R.id.et_search3)
        arr = ArrayList()
        bottomSheetDialog = context?.let { it1 -> BottomSheetDialog(it1, R.style.CustomBottomSheetDialogTheme) }
        bottomSheetDialog?.setContentView(R.layout.dialog3)
        bottomSheetDialog?.behavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        setSearch()
        getdesignation()

        view.imageView3.setOnClickListener {

            bottomSheetDialog?.findViewById<Button>(R.id.btn_add)?.setOnClickListener {

                var fname = bottomSheetDialog?.findViewById<EditText>(R.id.edit4)?.text
                var email = bottomSheetDialog?.findViewById<EditText>(R.id.edit5)?.text
                var designation2 = bottomSheetDialog?.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextviewSpinner)?.text
                var password = bottomSheetDialog?.findViewById<EditText>(R.id.edit7)?.text
            Log.d("response", "hbuhbi2 ${ bottomSheetDialog?.findViewById<EditText>(R.id.edit4)?.text} ")
                AddTeamRequest = AddTeamRequest(
                    name = fname.toString(),
                    email = email.toString(),
                    designation = "633417fe03eedcb84b6f823c",
                    bodyPassword = password.toString()
                )
                Log.e("flowCheck", "0 ${Gson().toJson(AddTeamRequest)}")

                addTamAPiCall()
            }
            bottomSheetDialog?.show()
        }
        getMyData5()
        return view
    }
    private fun getMyData5() {
        list.clear()
        rvData?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE
        val retrofitData = DashboardActivity.token?.let {
            RetrofitService.networkCall().getData5("Bearer $it")
            }
        retrofitData?.enqueue(object : Callback<response_teams?> {
            override fun onResponse(
                call: Call<response_teams?>,
                response: Response<response_teams?>
            ) {
                Log.e("response", "hbuhbi ${call.request()} ${response.isSuccessful}")
                if (response.isSuccessful) {
                    progressBar?.visibility = View.GONE
                    Log.e("response", "${response.body()?.data?.size}")
                    setUpAdapter()
                    list = (response.body()!!.data as MutableList<response_teams.Data?>?)!!
                    myListAdapter3.addData(list)
                }
            }
            override fun onFailure(call: Call<response_teams?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
                Log.e("response", "onfailre ${t.message}")
                progressBar?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun getdesignation() {
        rvData?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE
        val retrofitData =
            DashboardActivity.token?.let {
                RetrofitService.networkCall().getdesignation("Bearer $it")
            }
        retrofitData?.enqueue(object : Callback<getdesignationresp?> {
            override fun onResponse(
                call: Call<getdesignationresp?>,
                response: Response<getdesignationresp?>
            ) {
                Log.e("response", "hbuhbi ${call.request()} ${response.isSuccessful}")
                if (response.isSuccessful) {
                    progressBar?.visibility = View.GONE
                    Log.e("response", "${response.body()?.data?.size}")
                    for(i in response.body()?.data!!){
                        arr.add(i?.name!!)
                    }
                    var adapter = ArrayAdapter<String>(context!!,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arr)
                    autoCompleteTextView = bottomSheetDialog?.findViewById(R.id.autoCompleteTextviewSpinner)!!
                    autoCompleteTextView.setAdapter(adapter)
                    autoCompleteTextView.setOnClickListener{
                        autoCompleteTextView.showDropDown()
                    }
                }
            }
            override fun onFailure(call: Call<getdesignationresp?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
                Log.e("response", "onfailre ${t.message}")
                progressBar?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun addTamAPiCall(){
        Log.e("flowCheck", "1")
            rvData?.visibility = View.GONE
            progressBar?.visibility = View.VISIBLE
            val retrofitData =
            DashboardActivity.token?.let {
                RetrofitService.networkCall().addTeam("Bearer $it",AddTeamRequest)
            }
            retrofitData?.enqueue(object : Callback<AddTeamResponse> {
            override fun onResponse(
                call: Call<AddTeamResponse?>,
                response: Response<AddTeamResponse>
            ) {
                Log.e("flowCheck", "3 : ${call.request()}")
                Log.e("flowCheck", "6 : ${response.isSuccessful}")
                Log.e("flowCheck", "7 : ${response.code()}")
                Log.e("flowCheck", "8 : ${Gson().toJson(response.body())}")
                if (response.code() == 201) {
                    Log.e("flowCheck", "4 : ${response.body()}")
                    bottomSheetDialog?.hide()
                    Toast.makeText(requireContext(), "${response.body()?.message}", Toast.LENGTH_SHORT).show()
                    getMyData5()
                }
            }
                override fun onFailure(call: Call<AddTeamResponse?>, t: Throwable) {
                Log.e("flowCheck", "4 : ")
                progressBar?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
            }
            })
    }
    private fun setUpAdapter() {
        Log.d("TeamsFragment", "setUpAdapter: ${Gson().toJson(list)}")
        rvData?.visibility = View.VISIBLE
        val layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        rvData?.layoutManager = layoutManager
        myListAdapter3 = MyDataChildAdapter3(requireContext(), 0)
        rvData?.adapter = myListAdapter3
    }
    private fun setSearch() {
        searchView?.addTextChangedListener(object : TextWatcher {
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
    fun search(text: String) {
        filterdNames = mutableListOf()
        for (s in list) {

            var temp = s?.name
            if (temp?.toLowerCase()?.contains(text.toLowerCase()) == true) {
                filterdNames.add(s)
            }
        }
        if (::myListAdapter3.isInitialized) {
            myListAdapter3.addData(filterdNames)
        }
    }


}


