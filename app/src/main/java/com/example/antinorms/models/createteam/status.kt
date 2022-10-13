package com.example.antinorms.models.createteam

import com.google.gson.annotations.SerializedName

data class status (

    @SerializedName("status"  ) var status  : String?         = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Data3> = arrayListOf()

)