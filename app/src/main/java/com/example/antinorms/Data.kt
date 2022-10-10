package com.example.antinorms

import com.example.antinorms.models.Role.Role
import com.example.antinorms.models.project_update.designation
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("_id") var Id: String? = null,
    @SerializedName("firstName") var firstName: String? = null,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("isFresher") var isFresher: String? = null,
    @SerializedName("workingExperienceInMonths") var workingExperienceInMonths: Int? = null,
    @SerializedName("projects") var projects: ArrayList<Projects> = arrayListOf(),
    @SerializedName("isDeleted") var isDeleted: Boolean? = null,
    @SerializedName("joiningDate") var joiningDate: String? = null,
    @SerializedName("seniority") var seniority: String? = null,
    @SerializedName("remarks") var remarks: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("phoneNumber") var phoneNumber: String? = null,
    @SerializedName("emergencyContactNumber") var emergencyContactNumber: String? = null,
    @SerializedName("empId") var empId: String? = null,
    @SerializedName("isAvailable") var isAvailable: Boolean? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("reportingPm") var reportingPm: ReportingPm? = ReportingPm(),
    @SerializedName("role") var Role: Role? = null,
    @SerializedName("designation") var designation: designation? = null,
    @SerializedName("techStack") var techStack: TechStack? = null


    )