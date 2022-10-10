package com.example.antinorms

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.antinorms.RetrofitService.client
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BlankFragment : Fragment() {

    private var rvData:RecyclerView?= null
    private var progressBar:ProgressBar?= null
    private var llParent:LinearLayout?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_blank, container, false)
        rvData = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progresBar)
        llParent = view.findViewById(R.id.llParent)
        getMyData()

        return view
    }

    private fun getMyData() {
        rvData?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE
        val retrofitData =
                         DashboardActivity.token?.let {
                    RetrofitService.networkCall().getData("Bearer $it") }

        retrofitData?.enqueue(object :Callback<MyDataClassForApi?>{
            override fun onResponse(
                call: Call<MyDataClassForApi?>,
                response: Response<MyDataClassForApi?>
            ) {
                rvData?.visibility = View.VISIBLE
                progressBar?.visibility = View.GONE

                if(response.isSuccessful){
                    /*val adapter = response.body()?.let {it-> MyListAdapter(requireActivity(),it.data) }
                    rvData?.adapter = adapter
*/
                    Log.e("response","${response.body()?.data}")
                    setUpAdapter(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<MyDataClassForApi?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
                progressBar?.visibility = View.GONE
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()

            }

        })


    }

    private fun setUpAdapter( list : List<Data>) {

        Log.d("BlankFragment", "setUpAdapter: ${Gson().toJson(list)}")

        val headerList = mutableListOf<String>()

        headerList.add("Developer ID")
        headerList.add("Full name")
        headerList.add("Availability")


        val layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
        rvData?.layoutManager = layoutManager
        val myListAdapter = MyDataChildAdapter(requireContext(),list,0)
        rvData?.adapter = myListAdapter

    }



}