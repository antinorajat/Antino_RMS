package com.example.antinorms

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.antinorms.models.projectResp.ProjectResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


private var developer: TextView? = null
private var project: TextView? = null
private var available: TextView? = null
private var progressBar: ProgressBar? = null
private var llParent: LinearLayout? = null
var list = mutableListOf<Data>()


var developersize by Delegates.notNull<Int>()


class Dasboard : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val viewI = inflater.inflate(R.layout.fragment_dasboard, container, false)

        developer = viewI?.findViewById(R.id.text2)
        available = viewI?.findViewById(R.id.txt6)
        project = viewI?.findViewById(R.id.txt9)
        getMyData()
        getMyData2()


        return viewI

    }

    private fun getMyData2() {
        val retrofitData =
            DashboardActivity.token?.let {
                RetrofitService.networkCall().getData2("Bearer $it")
            }


        retrofitData?.enqueue(object : Callback<ProjectResponse?>{
            override fun onResponse(
                call: Call<ProjectResponse?>,
                response: Response<ProjectResponse?>
            ) {
                if (response.isSuccessful) {
                    /*val adapter = response.body()?.let {it-> MyListAdapter(requireActivity(),it.data) }
                    rvData?.adapter = adapter
*/
                    Log.e("response", "${response.body()?.profile}")
//                    setUpAdapter()
                    val size = response.body()?.profile?.size!!
                    project?.text = size.toString()

//                    developer?.text = size.toString()
                    Log.e("response", size.toString())
                } else {
                    Log.e("response", response.message())
                }
            }

            override fun onFailure(call: Call<ProjectResponse?>, t: Throwable) {

                Log.d("MainActivity12", "onFailure: " + t.message)
                progressBar?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

            }

        })


    }








    private fun getMyData() {
        val retrofitData =
            DashboardActivity.token?.let {
                RetrofitService.networkCall().getData("Bearer $it")
            }

        retrofitData?.enqueue(object : Callback<MyDataClassForApi?> {
            override fun onResponse(
                call: Call<MyDataClassForApi?>,
                response: Response<MyDataClassForApi?>
            ) {
                if (response.isSuccessful) {
                    /*val adapter = response.body()?.let {it-> MyListAdapter(requireActivity(),it.data) }
                    rvData?.adapter = adapter
*/
                    Log.e("response", "${response.body()?.data}")
//                    setUpAdapter()
                    val size =  response.body()?.data?.size!!
                    developer?.text = size.toString()
                    var counter = 0
                    for (i in response.body()!!.data){
                        if(i.isAvailable!=null){
                            counter++
                        }
                    }
                    available?.text = counter.toString()
//                    developer?.text = size.toString()
                    Log.e("response", size.toString())
                }else{
                    Log.e("response", response.message())
                }
            }

            override fun onFailure(call: Call<MyDataClassForApi?>, t: Throwable) {
                Log.d("MainActivity12", "onFailure: " + t.message)
                progressBar?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

            }

        })


    }

//    private fun setUpAdapter() {
//
//        Log.d("BlankFragment", "setUpAdapter: ${Gson().toJson(list)}")
//
//        val headerList = mutableListOf<String>()
//
//        headerList.add("Developer ID")
//        headerList.add("Full name")
//        headerList.add("Availability")
//
//
//        val layoutManager =
//            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
//        rvData?.layoutManager = layoutManager
//        myListAdapter = MyDataChildAdapter(requireContext(), 0){
//            getMyData()
//        }
//        rvData?.adapter = myListAdapter
//
//    }













    companion object {


        fun newInstance(param1: String, param2: String) =
            Dasboard().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}