package com.example.antinorms

import com.google.gson.annotations.SerializedName

data class SaveDevRes(
    @SerializedName("message"  ) var message  : String?  = null,
    @SerializedName("status"   ) var status   : Boolean? = null,
    @SerializedName("keyValue" ) var keyValue : String?  = null
)