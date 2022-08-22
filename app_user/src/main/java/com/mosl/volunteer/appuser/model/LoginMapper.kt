package com.mosl.volunteer.appuser.model

import com.mosl.data.api.login.LoginInfoRequest
import com.mosl.data.api.login.LoginInfoResponse
import com.mosl.data.api.login.RmDataResponse

fun LoginRequest.mapToLoginRequest(): LoginInfoRequest {
    return LoginInfoRequest(
        uniqueId = uniqueId,
        deviceId = deviceId
    )
}

fun LoginInfoResponse.mapToLoginResponse(): User {
    return User(
        clientId = clientId,
        fundId = fundId,
        clientName = clientName,
        emailId = emailId,
        fundName = fundName,
        contactName1 = contactName1,
        contactName2 = contactName2,
        contactName3 = contactName3,
        contactMobile1 = contactMobile1,
        contactMobile2 = contactMobile2,
        contactMobile3 = contactMobile3,
        day = day,
        role = role
    )
}

fun RmDataResponse.mapToRmData(rmResponseData:
                   RmDataResponse, message:String): RmUser {
    return RmUser(
        empCode = rmResponseData.empCode?:"",
        fundId = rmResponseData.fundId?:"",
        name = rmResponseData.name?:"",
        fundName = rmResponseData.fundName?:"",
        contactName1 = rmResponseData.contactName1?:"",
        contactName2 = rmResponseData.contactName2?:"",
        contactName3 = rmResponseData.contactName3?:"",
        roomNo = rmResponseData.roomNO?:"",
        companyName = rmResponseData.companyName?:"",
        message = message
    )
}