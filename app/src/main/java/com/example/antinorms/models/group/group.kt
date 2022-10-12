package com.example.antinorms.models.group

import com.google.gson.annotations.SerializedName

data class group (
    @SerializedName("_id") var Id        : String? = null,
    @SerializedName("group_name" ) var name      : String? = null,


)