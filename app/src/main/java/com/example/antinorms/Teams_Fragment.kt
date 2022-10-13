package com.example.antinorms

import android.annotation.SuppressLint
import android.app.DatePickerDialog
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
import com.example.antinorms.databinding.MyListAdapter3Binding
import com.example.antinorms.models.Register.registerdata
import com.example.antinorms.models.createteam.Data3
import com.example.antinorms.models.createteam.response_teams
import com.example.antinorms.models.createteam.status
import com.example.antinorms.models.createteam.teamsresp
import com.example.antinorms.models.registerresp
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_blank.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class Teams_Fragment : Fragment() {
    private var rvData: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var llParent: LinearLayout? = null
    private var bottomSheetDialog: BottomSheetDialog? = null
    private var searchView: EditText? = null
    var filterdNames = mutableListOf<Data3>()
    var list:List<response_teams.Data?> = arrayListOf()
    lateinit var myListAdapter3: MyDataChildAdapter3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_teams_, container, false)
        rvData = view.findViewById(R.id.recyclerView3)
        progressBar = view.findViewById(R.id.progresBar3)
        llParent = view.findViewById(R.id.llParent3)
        searchView = view.findViewById(R.id.et_search3)

        setSearch()



        getMyData5()


        return view
    }


    private fun getMyData5() {
        rvData?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE

        val retrofitData =
            DashboardActivity.token?.let {

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
                    list = response.body()!!.data!!
                    myListAdapter3.addData(response.body()!!.data)

//                    Toast.makeText(requireActivity(), response.body()?.message, Toast.LENGTH_SHORT).show()
//                    bottomSheetDialog?.dismiss()
                  //  getMyData5()

//                    Log.e("response", "${response.body()?.message}")

//                } else {
//                    Toast.makeText(requireContext(), "${response.body().toString()}", Toast.LENGTH_SHORT).show()
//                }
                }
                else{
//                    Log.d("aaa", "onFailure: " + t.message)
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


    private fun setUpAdapter() {

        Log.d("BlankFragment", "setUpAdapter: ${Gson().toJson(list)}")

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
              //  filterdNames.add(s)

            }
        }

        if (::myListAdapter3.isInitialized) {
          //  myListAdapter3.addData(filterdNames)
        }
    }


}
