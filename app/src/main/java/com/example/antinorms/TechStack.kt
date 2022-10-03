package com.example.antinorms



import com.google.gson.annotations.SerializedName


data class TechStack (
    @SerializedName("_id"       ) var Id        : String? = null,
    @SerializedName("name"      ) var name      : String? = null,
    @SerializedName("createdAt" ) var createdAt : String? = null,
    @SerializedName("updatedAt" ) var updatedAt : String? = null

)