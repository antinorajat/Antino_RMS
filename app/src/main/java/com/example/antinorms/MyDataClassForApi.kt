package com.example.antinorms

import com.google.gson.annotations.SerializedName

data class MyDataClassForApi (
    @SerializedName("status" ) var status : Boolean?        = null,
    @SerializedName("data"   ) var data   : ArrayList<Data> = arrayListOf(),
    @SerializedName("count"  ) var count  : Int?            = null

)