package com.example.antinorms.models.createteam

data class response_teams(
    val `data`: List<Data?>?,
    val message: String?,
    val status: String?
) {
    data class Data(
        val designation: Designation?,
        val email: String?,
        val group_id: GroupId?,
        val name: String?,
        val password: String?
    ) {
        data class Designation(
            val __v: Int?,
            val _id: String?,
            val createdAt: String?,
            val name: String?,
            val updatedAt: String?
        )

        data class GroupId(
            val Developers: List<String?>?,
            val __v: Int?,
            val _id: String?,
            val group_name: String?,
            val mentor: String?
        )
    }
}