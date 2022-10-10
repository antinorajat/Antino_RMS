package com.example.antinorms.models.Role

import com.google.gson.annotations.SerializedName


data class Role (
    @SerializedName("_id"       ) var Id        : String? = null,
    @SerializedName("name"      ) var name      : String? = null,
    @SerializedName("createdAt" ) var createdAt : String? = null,
    @SerializedName("updatedAt" ) var updatedAt : String? = null

)