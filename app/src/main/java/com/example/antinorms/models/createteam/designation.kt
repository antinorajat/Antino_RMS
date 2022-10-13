package com.example.antinorms.models.createteam

import com.google.gson.annotations.SerializedName

data class Designation (

    @SerializedName("_id"       ) var Id        : String? = null,
    @SerializedName("name"      ) var name      : String? = null,
    @SerializedName("createdAt" ) var createdAt : String? = null,
    @SerializedName("updatedAt" ) var updatedAt : String? = null,
    @SerializedName("__v"       ) var _v        : Int?    = null

)