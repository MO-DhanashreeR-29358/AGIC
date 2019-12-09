package com.mosl.mobile.data.bytes

import com.mosl.data.api.bytes.BytesInfoRequest
import com.mosl.mobile.domain.bytes.BytesRequest

fun BytesRequest.mapToBytesRequest(
): BytesInfoRequest {

    return BytesInfoRequest(
        bytesTimeStamp = bytesTimeStamp,
        scheduleTimeStamp = scheduleTimeStamp,
        clientId = clientId
    )
}