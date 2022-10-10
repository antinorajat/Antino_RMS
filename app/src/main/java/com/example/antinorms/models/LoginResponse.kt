package com.example.antinorms.models

data class LoginResponse(
    val code: String?,
    val msg: String,
    val status: Boolean,
    val data: Data?
)