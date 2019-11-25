package com.mosl.mobile.demo


data class AuthResponseM(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)