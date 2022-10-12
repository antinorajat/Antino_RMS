package com.example.antinorms.models.createproject

import com.google.gson.annotations.SerializedName

data class createProject (

    @SerializedName("projectName"                   ) var projectName                   : String? = null,
    @SerializedName("clientName"                    ) var clientName                    : String? = null,
    @SerializedName("techStack"                     ) var techStack                     : String? = null,
    @SerializedName("typeOfProject"                 ) var typeOfProject                 : String? = null,
    @SerializedName("startDate"                     ) var startDate                     : String? = null,
    @SerializedName("estimatedEndDate"              ) var estimatedEndDate              : String? = null,
    @SerializedName("demoUrls"                      ) var demoUrls                      : String? = null,
    @SerializedName("internalMeetingDaysAndTimings" ) var internalMeetingDaysAndTimings : String? = null,
    @SerializedName("clientMeetingDaysAndTimings"   ) var clientMeetingDaysAndTimings   : String? = null,
    @SerializedName("clientPointOfContact"          ) var clientPointOfContact          : String? = null,
    @SerializedName("status"                        ) var status                        : String? = null,
    @SerializedName("projectManager"                ) var projectManager                : String? = null


)