package com.example.antinorms.models.createteam

import com.google.gson.annotations.SerializedName

data class teamsresp(


    @SerializedName("status"  ) var status  : String? = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("data"    ) var data    : response_teams.Data?


)