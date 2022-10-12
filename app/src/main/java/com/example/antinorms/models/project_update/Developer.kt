package com.example.antinorms.models.project_update

data class Developer(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val designation: String,
    val email: String,
    val emergencyContactNumber: String,
    val empId: String,
    val firstName: String,
    val group: String,
    val isAvailable: String,
    val isDeleted: Boolean,
    val isFresher: Boolean,
    val joiningDate: String,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val projects: List<Any>,
    val remarks: String,
    val reportingPm: String,
    val role: String,
    val seniority: String,
    val techStack: String,
    val updatedAt: String,
    val workingExperienceInMonths: Int,
    val clientMeetingDaysAndTimings: String
)