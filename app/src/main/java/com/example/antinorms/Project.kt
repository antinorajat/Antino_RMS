package com.example.antinorms

data class Project(
    val _id: String,
    val clientMeetingDaysAndTimings: String,
    val clientName: String,
    val clientPointOfContact: String,
    val demoUrls: String,
    val developers: List<String>,
    val estimatedEndDate: String,
    val internalMeetingDaysAndTimings: String,
    val projectName: String,
    val startDate: String,
    val status: String,
    val techStack: String,
    val typeOfProject: String
)