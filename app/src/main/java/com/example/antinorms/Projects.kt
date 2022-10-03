package com.example.antinorms

import com.google.gson.annotations.SerializedName

data class Projects(
    @SerializedName("_id"                           ) var Id                            : String?           = null,
    @SerializedName("projectName"                   ) var projectName                   : String?           = null,
    @SerializedName("clientName"                    ) var clientName                    : String?           = null,
    @SerializedName("status"                        ) var status                        : String?           = null,
    @SerializedName("techStack"                     ) var techStack                     : String?           = null,
    @SerializedName("typeOfProject"                 ) var typeOfProject                 : String?           = null,
    @SerializedName("startDate"                     ) var startDate                     : String?           = null,
    @SerializedName("estimatedEndDate"              ) var estimatedEndDate              : String?           = null,
    @SerializedName("demoUrls"                      ) var demoUrls                      : String?           = null,
    @SerializedName("internalMeetingDaysAndTimings" ) var internalMeetingDaysAndTimings : String?           = null,
    @SerializedName("clientMeetingDaysAndTimings"   ) var clientMeetingDaysAndTimings   : String?           = null,
    @SerializedName("clientPointOfContact"          ) var clientPointOfContact          : String?           = null,
    @SerializedName("developers"                    ) var developers                    : ArrayList<String> = arrayListOf()

)
