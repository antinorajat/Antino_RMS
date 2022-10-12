package com.example.antinorms

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.antinorms.listeners.OnItemClickListener
import com.example.antinorms.models.projectResp.Profile


import com.example.antinorms.models.projectResp.ProjectResponse
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_blank2.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlankFragment3 : Fragment(), OnItemClickListener {

    private var rvData2: RecyclerView?= null
    private var progressBar2: ProgressBar?= null
    private var llParent2: LinearLayout?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_blank2, container, false)
        rvData2 = view.findViewById(R.id.recyclerView2)
        progressBar2 = view.findViewById(R.id.progresBar2)
        llParent2 = view.findViewById(R.id.llParent2)


        view.imageView1.setOnClickListener{
            val bottomSheetDialog = context?.let { it1 -> BottomSheetDialog(it1,R.style.CustomBottomSheetDialogTheme) }
            bottomSheetDialog?.setContentView(R.layout.dialog2)
            bottomSheetDialog?.behavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            bottomSheetDialog?.show()




    }

        getMyData2()

        return view
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