package com.example.antinorms.models.updateProjectModel.res

data class Profile(
    val acknowledged: Boolean,
    val matchedCount: Int,
    val modifiedCount: Int,
    val upsertedCount: Int,
    val upsertedId: Any
)