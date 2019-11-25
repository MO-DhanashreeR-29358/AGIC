package com.mosl.mobile.demo


data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)