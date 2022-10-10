package com.example.antinorms.models.project_update

data class pro_update(
    val count: Int,
    val message: String,
    val profile: List<Profile>,
    val status: Boolean
)