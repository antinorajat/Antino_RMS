package com.example.antinorms

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.antinorms.databinding.FragmentSplashScreenBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class SplashScreen : Fragment() {
    private lateinit var binding : FragmentSplashScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSplashScreenBinding.inflate(inflater, container, false)
        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPreferences: SharedPreferences =
                requireContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
            val token:String?=sharedPreferences.getString("token","")

            if(token?.isEmpty()==true)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_splashScreen2_to_fragment_login)
            else
            { startActivity(Intent(requireActivity(),DashboardActivity::class.java).putExtra("token",token))
            requireActivity().finish()}


        }, 3000)

        return binding.root
    }


}