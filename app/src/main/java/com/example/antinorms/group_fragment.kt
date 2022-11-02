package com.example.antinorms

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.antinorms.models.*
import com.example.antinorms.models.createteam.AddTeamRequest
import com.example.antinorms.models.createteam.AddTeamResponse
import com.example.antinorms.models.createteam.response_teams
import com.example.antinorms.models.getdesignation.getdesignationresp
import com.example.antinorms.sharedPreference.MyDataChildAdapter4
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams_.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class group_fragment : Fragment() {
    private var rvData: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var llParent: LinearLayout? = null
    private var param1: String? = null
    private var param2: String? = null
    private var bottomSheetDialog: BottomSheetDialog? = null
    lateinit var myListAdapter4: MyDataChildAdapter4
    private var listOfManagers = arrayListOf<String>()
    var filterdNames = mutableListOf<group_res.Data>()
    private var searchView: EditText? = null
    lateinit var AddgroupRequest: groupRequest
    lateinit var autoCompleteTextView: AutoCompleteTextView
    lateinit var arr: ArrayList<String>
    private lateinit var manager:AutoCompleteTextView
    var list = mutableListOf<group_res.Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_group_fragment, container, false)
        rvData = view.findViewById(R.id.recyclerView3)
        searchView = view.findViewById(R.id.et_search3)
        progressBar = view.findViewById(R.id.progresBar4)
        bottomSheetDialog =
            context?.let { it1 -> BottomSheetDialog(it1, R.style.CustomBottomSheetDialogTheme) }
        bottomSheetDialog?.setContentView(R.layout.dialog4)

        manager  = bottomSheetDialog?.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextviewSpinner2)!!
        bottomSheetDialog?.findViewById<Button>(R.id.btn_add)?.setOnClickListener {
            var fname = bottomSheetDialog?.findViewById<EditText>(R.id.edit4)?.text
            Log.e("listOfManagers","$listOfManagers")
            val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("MySharedPref",
                Context.MODE_PRIVATE
            )
            AddgroupRequest = groupRequest(groupName= fname.toString(), sharedPreferences.getString("userId",""))
            Log.e("flowCheck1", "0 ${Gson().toJson(AddgroupRequest)}")
            Log.e("userId--->", "0 ${sharedPreferences.getString("userId","")}")
            addTamAPiCall()

        }
        setSearch()
        getMyData6()
        view.imageView3.setOnClickListener {
            bottomSheetDialog?.show()
        }
        getMyData6()
        return view
    }
    companion object {
        fun newInstance(param1: String, param2: String) =
            group_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun getMyData6() {
        list.clear()
        rvData?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE

        val retrofitData = DashboardActivity.token?.let {

            RetrofitService.networkCall().getData6("Bearer $it")
        }
        retrofitData?.enqueue(object : Callback<group_res?> {
            override fun onResponse(
                call: Call<group_res?>,
                response: Response<group_res?>
            ) {
                Log.e("response", "hbuhbi ${call.request()} ${response.isSuccessful}")
                if (response.isSuccessful) {
                    progressBar?.visibility = View.GONE
                    listOfManagers.clear()
                    listOfManagers.addAll(response.body()?.data?.map { it.mentor?.name }?.distinct() as ArrayList<String>)
                    var adapter = ArrayAdapter(requireContext(),
                        androidx.appcompat.R.layout.select_dialog_item_material,listOfManagers)
                    manager.setAdapter(adapter)
                    manager.setOnClickListener{
                        manager.showDropDown()
                    }
                    setUpAdapter()
                    list.clear()
                    list.addAll(response.body()!!.data as MutableList<group_res.Data>)
                    myListAdapter4.addData(list)
                }
            }

            override fun onFailure(call: Call<group_res?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
                Log.e("response", "onfailre ${t.message}")
                progressBar?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

            }

        })

    }
    fun addTamAPiCall(){
        rvData?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE
        val retrofitData =
            DashboardActivity.token?.let {
                RetrofitService.networkCall().addgroup("Bearer $it",AddgroupRequest)
            }


        retrofitData?.enqueue(object : Callback<GroupAdd> {
            override fun onResponse(
                call: Call<GroupAdd?>,
                response: Response<GroupAdd>
            ) {
                Log.e("flowCheck", "3 : ${call.request()}")
                Log.e("flowCheck", "6 : ${response.isSuccessful}")
                Log.e("flowCheck", "7 : ${response.code()}")
                Log.e("flowCheck", "8 : ${Gson().toJson(response.body())}")

//                if (response.code() == 201) {
                    Log.e("flowCheck", "4 : ${response.body()}")
                    bottomSheetDialog?.hide()
                    Toast.makeText(requireContext(), "${response.body()?.message}", Toast.LENGTH_SHORT).show()
                    getMyData6()


            }

            override fun onFailure(call: Call<GroupAdd?>, t: Throwable) {
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
        myListAdapter4 = MyDataChildAdapter4(requireContext())
        rvData?.adapter = myListAdapter4

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
            var temp = s?.group_name
            if (temp?.lowercase()?.contains(text.lowercase()) == true) {
                filterdNames.add(s)

            }
        }

        if (::myListAdapter4.isInitialized) {
            myListAdapter4.addData(filterdNames)
        }
    }



    private fun getgroupdata() {
        list.clear()
        rvData?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE

        val retrofitData = DashboardActivity.token?.let {

            RetrofitService.networkCall().getgroupdata("Bearer $it")
        }
        retrofitData?.enqueue(object : Callback<addgroup?> {
            override fun onResponse(
                call: Call<addgroup?>,
                response: Response<addgroup?>
            ) {
                Log.e("response", "hbuhbi ${call.request()} ${response.isSuccessful}")
                if (response.isSuccessful) {
                    progressBar?.visibility = View.GONE
                    Log.e("response", "${response.body()?.data?.size}")
                    setUpAdapter()
//                    list.clear()
                    list.addAll(response.body()!!.data as MutableList<group_res.Data>)
                    myListAdapter4.addData(list)
                }
            }

            override fun onFailure(call: Call<addgroup?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
                Log.e("response", "onfailre ${t.message}")
                progressBar?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

            }

        })

    }





}











