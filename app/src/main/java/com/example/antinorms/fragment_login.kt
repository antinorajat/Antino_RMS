package com.example.antinorms

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.antinorms.databinding.FragmentLoginBinding
import com.example.antinorms.models.LoginReq
import com.example.antinorms.models.LoginResponse
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log



private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class fragment_login : Fragment() {


    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    private lateinit var _binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_login().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("MySharedPref", MODE_PRIVATE)

        _binding.loginButton.setOnClickListener {
            val email = _binding.etEmail.text.toString()
            val password = _binding.etpassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                RetrofitService.networkCall().login(LoginReq(email, password))
                    .enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            if (response.isSuccessful)
                            {

                                    sharedPreferences.edit().putString("token",response.body()?.data?.token).apply()
                                sharedPreferences.edit()?.putString("email", email)?.apply()
                                    sharedPreferences.edit()?.putString("password", password)?.apply()


                                Log.e("response", "${response.body()}")
                                startActivity(Intent(requireActivity(),DashboardActivity::class.java).putExtra("token",response.body()?.data?.token))
                                requireActivity().finish()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT)
                                .show()
                        }

                    })
            }
        }
    }
}