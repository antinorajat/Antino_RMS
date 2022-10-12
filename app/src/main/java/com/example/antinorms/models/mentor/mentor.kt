package com.example.antinorms.models.mentor

import com.google.gson.annotations.SerializedName
data class mentor (

    @SerializedName("user_name" ) var userName : String? = null,
    @SerializedName("email"     ) var email    : String? = null,
    @SerializedName("name"      ) var name     : String? = null

)