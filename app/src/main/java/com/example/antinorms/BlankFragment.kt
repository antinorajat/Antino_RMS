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
import com.example.antinorms.models.registerresp
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_blank2.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class BlankFragment : Fragment() {

    private var rvData: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var llParent: LinearLayout? = null
    private var bottomSheetDialog : BottomSheetDialog? = null
    private var searchView : EditText? = null
    var filterdNames = mutableListOf<Data>()
    var list = mutableListOf<Data>()
    lateinit var myListAdapter : MyDataChildAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_blank, container, false)
        rvData = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progresBar)
        llParent = view.findViewById(R.id.llParent)
        searchView = view.findViewById(R.id.et_search)

        setSearch()


        view.imageView1.setOnClickListener {
            bottomSheetDialog =
                context?.let { it1 -> BottomSheetDialog(it1, R.style.CustomBottomSheetDialogTheme) }
            bottomSheetDialog?.setContentView(R.layout.dialog)
            bottomSheetDialog?.behavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            var firstname = bottomSheetDialog?.findViewById<EditText>(R.id.edit)
            var lastname = bottomSheetDialog?.findViewById<EditText>(R.id.edit1)
            var email = bottomSheetDialog?.findViewById<EditText>(R.id.edit2)
            var dateJoining = bottomSheetDialog?.findViewById<EditText>(R.id.edit3)
            var designation = bottomSheetDialog?.findViewById<EditText>(R.id.edit4)
            var role = bottomSheetDialog?.findViewById<EditText>(R.id.edit5)
            var techstack = bottomSheetDialog?.findViewById<EditText>(R.id.edit6)
            var MobileNumber = bottomSheetDialog?.findViewById<EditText>(R.id.edit7)
            var emergencyContact = bottomSheetDialog?.findViewById<EditText>(R.id.edit8)
            var antinoId = bottomSheetDialog?.findViewById<EditText>(R.id.edit9)
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
                    joiningDate = dateJoining?.text.toString(),
                    designation = designation?.text.toString(),
                    role = role?.text.toString(),
                    techStack = techstack?.text.toString(),
                    phoneNumber = MobileNumber?.text.toString(),
                    emergencyContactNumber = emergencyContact?.text.toString(),
                    empId = antinoId?.text.toString()
                )
                getMyData3(registerdata)
            }
            bottomSheetDialog?.show()


            /*bottomSheetDialog?.behavior?.maxHeight = 1000
            bottomSheetDialog?.behavior?.peekHeight = 400*/

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

                    Log.e("response", "${response.body()?.message}")

                } else {
                    Toast.makeText(requireContext(), "${response.body().toString()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<registerresp?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
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
        myListAdapter = MyDataChildAdapter(requireContext(), 0)
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