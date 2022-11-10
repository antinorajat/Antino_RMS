package com.example.antinorms

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
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
import com.example.antinorms.models.Register.registerdata
import com.example.antinorms.models.getdesignation.getdesignationresp
import com.example.antinorms.models.registerresp
import com.example.antinorms.models.role
import com.example.antinorms.models.teckstack.techresp
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_blank.view.*
import kotlinx.android.synthetic.main.fragment_blank2.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class BlankFragment : Fragment() {

    private var rvData: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var llParent: LinearLayout? = null
    private var bottomSheetDialog : BottomSheetDialog? = null
    private var searchView : EditText? = null
    var filterdNames = mutableListOf<Data>()
    var list = mutableListOf<Data>()
    lateinit var myListAdapter : MyDataChildAdapter
    var autoCompleteTextView: AutoCompleteTextView?=null
     var arr1: ArrayList<String> = ArrayList()
    var autoCompleteTextView2: AutoCompleteTextView?=null
    var arr2: ArrayList<String> = ArrayList()
    var autoCompleteTextView3: AutoCompleteTextView?=null
    var arr3: ArrayList<String> = ArrayList()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_blank, container, false)
        bottomSheetDialog = BottomSheetDialog(view.context, R.style.CustomBottomSheetDialogTheme)

        rvData = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progresBar)
        llParent = view.findViewById(R.id.llParent)
        searchView = view.findViewById(R.id.et_search)
        setSearch()

        view.imageView1.setOnClickListener {
            bottomSheetDialog?.setContentView(R.layout.dialog)
            bottomSheetDialog?.behavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            var firstname = bottomSheetDialog?.findViewById<EditText>(R.id.edit)
            var lastname = bottomSheetDialog?.findViewById<EditText>(R.id.edit1)
            var email = bottomSheetDialog?.findViewById<EditText>(R.id.edit2)
            var dateJoining = bottomSheetDialog?.findViewById<EditText>(R.id.edit3)
            var designation = bottomSheetDialog?.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextview1)
            var role = bottomSheetDialog?.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextview2)
            var techstack = bottomSheetDialog?.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextview3)
            var MobileNumber = bottomSheetDialog?.findViewById<EditText>(R.id.edit7)
            var emergencyContact = bottomSheetDialog?.findViewById<EditText>(R.id.edit8)
            var antinoId = bottomSheetDialog?.findViewById<EditText>(R.id.edit9)
            var workingexperincemonth = bottomSheetDialog?.findViewById<EditText>(R.id.edit10)

            designation?.setOnClickListener {
                getdesignation()
            }

            role?.setOnClickListener {
                getrole()

            }

            techstack?.setOnClickListener {
                gettech()

            }
            var  c:Calendar = Calendar.getInstance();
            var year = c.get(Calendar.YEAR);
          var month = c.get(Calendar.MONTH);
           var day = c.get(Calendar.DAY_OF_MONTH)
            dateJoining?.keyListener=null
            dateJoining?.setOnClickListener {

                val datePickerDialog = DatePickerDialog(requireContext(),
                    OnDateSetListener { datePicker, i, i1, i2 ->
                        var i1 = i1
                        val date = i2.toString() + "/" + ++i1 + "/" + i
                        dateJoining.setText(date)
                    }, year, month, day
                )
                datePickerDialog.show()}

            bottomSheetDialog?.findViewById<Button>(R.id.btn_register)?.setOnClickListener {
                val registerdata = registerdata(
                    firstName = firstname?.text.toString(),
                    lastName = lastname?.text.toString(),
                    email = email?.text.toString(),
                    joiningDate = dateJoining?.text.toString().replace("/",""),
                    designation = designation?.text.toString(),
                    role = role?.text.toString(),
                    techStack = techstack?.text.toString(),
                    phoneNumber = MobileNumber?.text.toString(),
                    emergencyContactNumber = emergencyContact?.text.toString(),
                    empId = antinoId?.text.toString(),
                    workingExperienceInMonths = Integer.parseInt(workingexperincemonth?.text.toString())
                )
                getMyData3(registerdata)
            }
            bottomSheetDialog?.show()
        }

        getMyData()

        return view
    }


    private fun getMyData3(registerdata1: registerdata) {
        rvData?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE
        Log.d("BlankFragment", Gson().toJson(registerdata1))
        val retrofitData =
            DashboardActivity.token?.let {
                RetrofitService.networkCall().getData3("Bearer $it", registerdata1)
            }

        retrofitData?.enqueue(object : Callback<registerresp?> {
            override fun onResponse(
                call: Call<registerresp?>,
                response: Response<registerresp?>
            ) {

                if (response.isSuccessful) {

                    /*val adapter = response.body()?.let {it-> MyListAdapter(requireActivity(),it.data) }
                    rvData?.adapter = adapter
*/
                    Toast.makeText(requireActivity(), response.body()?.message, Toast.LENGTH_SHORT).show()
                    bottomSheetDialog?.dismiss()
                    getMyData()

                    Log.e("responjhhhhse", "${response.body()}")

                } else {
                    Toast.makeText(requireContext(), "${response.body().toString()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<registerresp?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + call.request()+"---"+t.message)
                progressBar?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

            }

        })


    }


    private fun getdesignation() {
//        rvData?.visibility = View.GONE
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
                        arr1.add(i?.name!!)
                    }
                    var adapter = ArrayAdapter<String>(context!!, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arr1)
                    autoCompleteTextView = bottomSheetDialog?.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextview1)
                    autoCompleteTextView?.setAdapter(adapter)

                    autoCompleteTextView?.showDropDown()


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

    private fun getrole() {
//        rvData?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE

        val retrofitData =
            DashboardActivity.token?.let {

                RetrofitService.networkCall().getrole("Bearer $it")
            }


        retrofitData?.enqueue(object : Callback<role?> {
            override fun onResponse(
                call: Call<role?>,
                response: Response<role?>
            ) {
                Log.e("response", "hbuhbi ${call.request()} ${response.isSuccessful}")

                if (response.isSuccessful) {
                    progressBar?.visibility = View.GONE
                    Log.e("response", "${response.body()?.data?.size}")

                    for(i in response.body()?.data!!){
                        arr1.add(i?.name!!)
                    }
                    var adapter = ArrayAdapter<String>(context!!, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arr1)
                    autoCompleteTextView = bottomSheetDialog?.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextview2)
                    autoCompleteTextView?.setAdapter(adapter)

                    autoCompleteTextView?.showDropDown()


                }

            }

            override fun onFailure(call: Call<role?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
                Log.e("response", "onfailre ${t.message}")
                progressBar?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

            }

        })


    }


    private fun gettech() {
//        rvData?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE

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
                    progressBar?.visibility = View.GONE
                    Log.e("response", "${response.body()?.data?.size}")

                    for(i in response.body()?.data!!){
                        arr1.add(i?.name!!)
                    }
                    var adapter = ArrayAdapter<String>(context!!, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arr1)
                    autoCompleteTextView = bottomSheetDialog?.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextview3)
                    autoCompleteTextView?.setAdapter(adapter)

                    autoCompleteTextView?.showDropDown()


                }

            }

            override fun onFailure(call: Call<techresp?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
                Log.e("response", "onfailre ${t.message}")
                progressBar?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

            }

        })

    }

    private fun getMyData() {
        list.clear()

        rvData?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE
        val retrofitData =
            DashboardActivity.token?.let {
                RetrofitService.networkCall().getData("Bearer $it")
            }

        retrofitData?.enqueue(object : Callback<MyDataClassForApi?> {
            override fun onResponse(
                call: Call<MyDataClassForApi?>,
                response: Response<MyDataClassForApi?>
            ) {
                rvData?.visibility = View.VISIBLE
                progressBar?.visibility = View.GONE

                if (response.isSuccessful) {
                    /*val adapter = response.body()?.let {it-> MyListAdapter(requireActivity(),it.data) }
                    rvData?.adapter = adapter
*/
                    Log.e("response", "${response.body()?.data}")
                    setUpAdapter()
                    list = response.body()!!.data
                    myListAdapter.addData(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<MyDataClassForApi?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
                progressBar?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

            }

        })


    }

    private fun setUpAdapter() {

        Log.d("BlankFragment", "setUpAdapter: ${Gson().toJson(list)}")

        val headerList = mutableListOf<String>()

        headerList.add("Developer ID")
        headerList.add("Full name")
        headerList.add("Availability")


        val layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        rvData?.layoutManager = layoutManager
        myListAdapter = MyDataChildAdapter(requireContext(), 0){
            getMyData()
        }
        rvData?.adapter = myListAdapter

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
    private fun search(text: String) {

        filterdNames = mutableListOf()
        for (s in list) {

            var temp = s.firstName+" "+s.lastName
            if (temp.toLowerCase().contains(text.toLowerCase())) {
                filterdNames.add(s)

            }
        }

        if (::myListAdapter.isInitialized){
            myListAdapter.addData(filterdNames)
        }
    }




}