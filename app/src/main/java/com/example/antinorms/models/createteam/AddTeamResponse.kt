package com.example.antinorms.models.createteam

data class AddTeamResponse(
    val data: Data?,
    val message: String?,
    val status: String?
) {
    data class Data(
        val __v: Int?,
        val _id: String?,
        val designation: String?,
        val email: String?,
        val group_id: Any?,
        val name: String?,
        val password: String?
    )
}