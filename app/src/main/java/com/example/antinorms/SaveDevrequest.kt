package com.example.antinorms

import com.google.gson.annotations.SerializedName

data class SaveDevrequest(

    @SerializedName("designation"               ) var designation               : String? = null,
    @SerializedName("role"                      ) var role                      : String? = null,
    @SerializedName("techStack"                 ) var techStack                 : String? = null,
    @SerializedName("firstName"                 ) var firstName                 : String? = null,
    @SerializedName("empId"                     ) var empId                     : String? = null,
    @SerializedName("emergencyContactNumber"    ) var emergencyContactNumber    : String? = null,
    @SerializedName("phoneNumber"               ) var phoneNumber               : String? = null,
    @SerializedName("email"                     ) var email                     : String? = null,
    @SerializedName("workingExperienceInMonths" ) var workingExperienceInMonths : Int?    = null,
    @SerializedName("joiningDate"               ) var joiningDate               : String? = null,
    @SerializedName("lastName"                  ) var lastName                  : String? = null,
    @SerializedName("remarks"                   ) var remarks                   : String? = null



)

