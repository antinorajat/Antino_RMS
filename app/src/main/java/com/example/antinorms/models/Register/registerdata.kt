package com.example.antinorms.models.Register

import com.google.gson.annotations.SerializedName

data class registerdata(

    @SerializedName("firstName") var firstName: String? = null,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("phoneNumber") var phoneNumber: String? = null,
    @SerializedName("techStack") var techStack: String? = null,
    @SerializedName("designation") var designation: String? = null,
    @SerializedName("isFresher") var isFresher: Boolean? = null,
    @SerializedName("role") var role: String? = null,
    @SerializedName("workingExperienceInMonths") var workingExperienceInMonths: Int? = null,
    @SerializedName("projects") var projects: ArrayList<String> = arrayListOf(),
    @SerializedName("joiningDate") var joiningDate: String? = null,
    @SerializedName("seniority") var seniority: String? = null,
    @SerializedName("reportingPm") var reportingPm: String? = null,
    @SerializedName("emergencyContactNumber") var emergencyContactNumber: String? = null,
    @SerializedName("empId") var empId: String? = null,
    @SerializedName("isAvailable") var isAvailable: Boolean? = null

)