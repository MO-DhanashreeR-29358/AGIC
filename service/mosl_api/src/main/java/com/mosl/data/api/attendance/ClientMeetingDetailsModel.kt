package com.mosl.data.api.attendance

data class ClientMeetingDetailsModel(
    val userId: String,
    val clientId: Int,
    val clientName: String,
    val fundId: Int,
    val fundName: String,
    val companyId: String,
    val companyName: String,
    val status: ClientStatus,
    val meetingId: Int,
    val startTime: Long,
    val endTime: Long,
    val isAllowed: AllowedStatus
)

enum class AllowedStatus {
    YES,
    NO
}

enum class ClientStatus {
    YES,
    NO
}