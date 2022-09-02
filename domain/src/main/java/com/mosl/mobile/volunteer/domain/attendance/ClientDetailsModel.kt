package com.mosl.mobile.volunteer.domain.attendance

data class ClientDetailsModel(
    val userId: String,
    val clientId: Int,
    val clientName: String,
    val fundId: Int,
    val fundName: String,
    val companyId: String,
    val companyName: String,
    val status: String,
    val meetingId: Int,
    val startTime: String,
    val endTime: String,
    val isAllowed: Int
)