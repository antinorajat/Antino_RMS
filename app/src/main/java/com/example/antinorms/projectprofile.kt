package com.example.antinorms

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class projectprofile (
    @SerializedName("status"  ) var status  : Boolean?           = null,
    @SerializedName("message" ) var message : String?            = null,
    @SerializedName("profile" ) var profile : ArrayList<Profile> = arrayListOf(),
    @SerializedName("count"   ) var count   : Int?               = null
)







