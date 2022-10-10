package com.example.antinorms.models.projectResp

data class Profile(
    val __v: Int,
    val _id: String,
    val clientMeetingDaysAndTimings: String,
    val clientName: String,
    val clientPointOfContact: String,
    val demoUrls: String,
    val developers: List<Developer>,
    val estimatedEndDate: String,
    val internalMeetingDaysAndTimings: String,
    val projectManager: ProjectManager,
    val projectName: String,
    val startDate: String,
    val status: String,
    val techStack: List<TechStack>,
    val typeOfProject: String
)