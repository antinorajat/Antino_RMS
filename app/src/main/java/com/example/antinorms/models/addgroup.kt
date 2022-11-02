package com.example.antinorms.models

data class addgroup(
    val `data`: List<Data?>?,
    val message: String?,
    val status: String?
) {
    data class Data(
        val Developers: List<Developer?>?,
        val __v: Int?,
        val _id: String?,
        val group_name: String?,
        val mentor: Mentor?
    ) {
        data class Developer(
            val __v: Int?,
            val _id: String?,
            val createdAt: String?,
            val designation: String?,
            val email: String?,
            val emergencyContactNumber: String?,
            val empId: String?,
            val firstName: String?,
            val group: String?,
            val isAvailable: String?,
            val isDeleted: Boolean?,
            val isFresher: Boolean?,
            val joiningDate: String?,
            val lastName: String?,
            val password: String?,
            val phoneNumber: String?,
            val projects: List<Any?>?,
            val remarks: String?,
            val reportingPm: String?,
            val role: String?,
            val seniority: String?,
            val techStack: String?,
            val updatedAt: String?,
            val workingExperienceInMonths: Int?
        )

        data class Mentor(
            val __v: Int?,
            val _id: String?,
            val designation: String?,
            val email: String?,
            val group_id: String?,
            val name: String?,
            val password: String?
        )
    }
}