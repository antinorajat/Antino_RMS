package com.example.antinorms.models

data class GroupAdd(
    val `data`: Data?,
    val message: String?,
    val status: String?
) {
    data class Data(
        val Developers: List<Any?>?,
        val __v: Int?,
        val _id: String?,
        val group_name: String?,
        val mentor: String?
    )
}