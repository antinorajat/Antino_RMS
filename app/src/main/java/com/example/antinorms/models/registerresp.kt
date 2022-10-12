package com.example.antinorms.models

import com.google.gson.annotations.SerializedName


data class registerresp(

    @SerializedName("status") var status: Boolean? = null,
    @SerializedName("message") var message: String?

)