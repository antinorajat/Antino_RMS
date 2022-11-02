package com.example.antinorms.models.updateProjectModel.req

import com.example.antinorms.models.projectResp.ProjectManager
import com.example.antinorms.models.projectResp.TechStack
import com.google.gson.annotations.SerializedName

data class UpdateData(

    @SerializedName("clientMeetingDaysAndTimings") var clientMeetingDaysAndTimings: String? = null,
    @SerializedName("clientName") var clientName: String? = null,
    @SerializedName("clientPointOfContact") var clientPointOfContact: String? = null,
    @SerializedName("estimatedenddate") var estimatedenddate: String? = null,
    @SerializedName("internalMeetingDaysAndTimings") var internalMeetingDaysAndTimings: String? = null,
    @SerializedName("projectmanager") var projectmanager: String? = null,
    @SerializedName("startdate") var startdate: String? = null,
    @SerializedName("techStack") var techStack: List<TechStack> = arrayListOf(),
    @SerializedName("typeOfProject") var typeOfProject: String?=null

)