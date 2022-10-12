package com.example.antinorms.models.updateProjectModel.req

data class UpdateData(
    val clientMeetingDaysAndTimings: String,
    val clientName: String,
    val clientPointOfContact: String,
    val internalMeetingDaysAndTimings: String,
    val techStack: List<String>,
    val typeOfProject: String,
    val  projectmanager:String,
    val estimatedenddate:String,
    val startdate:String
)