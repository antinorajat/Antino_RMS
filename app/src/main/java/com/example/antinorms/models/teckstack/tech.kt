package com.example.antinorms.models.teckstack

data class techresp(
    val `data`: List<Data?>?,
    val message: String?,
    val status: Boolean?
) {
    data class Data(
        val __v: Int?,
        val _id: String?,
        val createdAt: String?,
        val name: String?,
        val updatedAt: String?
    )
}