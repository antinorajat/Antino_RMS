package com.example.antinorms.models

data class role(
    val data: List<Data>,
    val message: String?,
    val status: Boolean?
) {
    data class Data(
        val _id: String?,
        val createdAt: String?,
        val name: String?,
        val updatedAt: String?
    )
}