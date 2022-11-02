package com.example

import com.google.gson.annotations.SerializedName

data class UpdateProjectReq(
    @SerializedName("clientMeetingDaysAndTimings")
    val clientMeetingDaysAndTimings: String?,
    @SerializedName("clientName")
    val clientName: String?,
    @SerializedName("clientPointOfContact")
    val clientPointOfContact: String?,
    @SerializedName("demoUrls")
    val demoUrls: String?,
    @SerializedName("estimatedEndDate")
    val estimatedEndDate: String?,
    @SerializedName("internalMeetingDaysAndTimings")
    val internalMeetingDaysAndTimings: String?,
    @SerializedName("projectName")
    val projectName: String?,
    @SerializedName("startDate")
    val startDate: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("techStack")
    val techStack: List<String?>?,
    @SerializedName("typeOfProject")
    val typeOfProject: String?
)