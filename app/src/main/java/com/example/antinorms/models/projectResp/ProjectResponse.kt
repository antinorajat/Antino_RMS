package com.example.antinorms.models.projectResp

data class ProjectResponse(
    val count: Int,
    val message: String,
    val profile: List<Profile>,
    val status: Boolean
)