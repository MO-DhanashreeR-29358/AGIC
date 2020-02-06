package com.mosl.mobile.app_user.model

import com.mosl.data.api.login.LoginInfoRequest
import com.mosl.data.api.login.LoginInfoResponse


fun LoginRequest.mapToLoginRequest(): LoginInfoRequest {
    return LoginInfoRequest(

        uniqueId=uniqueId,
        deviceId = deviceId

    )
}


fun LoginInfoResponse.mapToLoginResponse(): User {
    return User(

        clientId=clientId, fundId = fundId,clientName =  clientName,emailId =  emailId,fundName =  fundName,
        contactName1 =  contactName1,contactName2 =  contactName2,contactName3 =  contactName3,
        contactMobile1 = contactMobile1,contactMobile2 =  contactMobile2,contactMobile3 =  contactMobile3,day =  day


    )
}