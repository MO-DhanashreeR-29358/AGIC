package com.mosl.mobile.data.bytes

import com.mosl.data.api.AGICApi
import com.mosl.mobile.domain.bytes.BytesRepository
import com.mosl.mobile.domain.bytes.BytesRequest
import io.reactivex.Observable
import javax.inject.Inject

class BytesRepositoryImpl @Inject constructor(
    var agicApi: AGICApi
):BytesRepository {

    override fun bytesData(request: BytesRequest): Observable<List<String>> {

           val requestMapper =request.mapToBytesRequest()


        return agicApi.getByteDetails(requestMapper)
    }
}