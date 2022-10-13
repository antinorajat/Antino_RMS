package com.example.antinorms.models.createteam

import com.google.gson.annotations.SerializedName

data class teams (

    @SerializedName("name"         ) var name         : String? = null,
    @SerializedName("email"        ) var email        : String? = null,
    @SerializedName("designation"  ) var designation  : String? = null,
    @SerializedName("bodyPassword" ) var bodyPassword : String? = null

)