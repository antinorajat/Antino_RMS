package com.example.antinorms.models.createteam

import com.example.antinorms.models.group.group
import com.example.antinorms.models.mentor.mentor
import com.example.antinorms.models.project_update.Developer
import com.google.gson.annotations.SerializedName


data class Data3 (

    @SerializedName("_id"         ) var Id          : String?      = null,
    @SerializedName("name"        ) var name        : String?      = null,
    @SerializedName("email"       ) var email       : String?      = null,
    @SerializedName("designation" ) var designation : Designation? = Designation(),
    @SerializedName("group_id"    ) var groupId     : Any?      =null,
    @SerializedName("__v"         ) var _v          : Int?         = null,




)