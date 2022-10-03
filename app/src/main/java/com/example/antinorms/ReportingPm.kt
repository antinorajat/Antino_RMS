package com.example.antinorms

import com.google.gson.annotations.SerializedName

data class ReportingPm(

    @SerializedName("_id"         ) var Id          : String? = null,
    @SerializedName("name"        ) var name        : String? = null,
    @SerializedName("email"       ) var email       : String? = null,
    @SerializedName("designation" ) var designation : String? = null,
    @SerializedName("group_id"    ) var groupId     : String? = null
)