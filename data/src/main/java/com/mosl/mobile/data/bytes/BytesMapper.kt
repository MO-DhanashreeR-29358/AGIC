package com.mosl.mobile.data.bytes

import com.mosl.data.api.bytes.BytesInfoRequest
import com.mosl.data.api.bytes.BytesInfoResponse
import com.mosl.mobile.domain.bytes.BytesRequest
import com.mosl.mobile.domain.bytes.BytesResponse

fun BytesRequest.mapToBytesRequest(
): BytesInfoRequest {

    return BytesInfoRequest(
        bytesTimeStamp = bytesTimeStamp,
        scheduleTimeStamp = scheduleTimeStamp,
        clientId = clientId
    )
}


fun List<BytesInfoResponse>.mapToBytesResponse(
): List<BytesResponse> {

    return map {  BytesResponse(
        serialNumber =it.serialNumber,
        byte =it.byte,
        entryDate =it.entryDate
    )
    }
}