package com.example.antinorms.models.createteam

import com.google.gson.annotations.SerializedName

data class teamsresp (

    @SerializedName("index"      ) var index      : Int?        = null,
    @SerializedName("code"       ) var code       : Int?        = null,
    @SerializedName("keyPattern" ) var keyPattern : KeyPattern? = KeyPattern(),
    @SerializedName("keyValue"   ) var keyValue   : KeyValue?   = KeyValue()

)