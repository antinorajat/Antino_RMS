package com.example.antinorms

import com.google.gson.annotations.SerializedName


data class projectupdates (

 @SerializedName("id"         ) var id         : String?     = null,
 @SerializedName("updateData" ) var updateData : UpdateData? = UpdateData()

)