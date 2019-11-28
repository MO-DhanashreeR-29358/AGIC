package com.mosl.mobile.app_user.model

import com.mosl.data.api.login.LoginInfoRequest
import com.mosl.data.api.login.LoginInfoResponse


fun LoginRequest.mapToLoginRequest(): LoginInfoRequest {
    return LoginInfoRequest(

        uniqueId, deviceId

    )
}


fun LoginInfoResponse.mapToLoginResponse(): LoginResponse {
    return LoginResponse(

        clientId, fundId, clientName, emailId, fundName, contactName1, contactName2, contactName3, contactMobile1, contactMobile2, contactMobile3, day


    )
}