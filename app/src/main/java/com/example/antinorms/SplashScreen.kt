package com.example.antinorms

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.antinorms.R
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
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_splashScreen_to_loginFragment)

        }, 3000)

        return binding.root
    }


}