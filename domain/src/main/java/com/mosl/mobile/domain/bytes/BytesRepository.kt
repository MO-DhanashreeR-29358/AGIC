package com.mosl.mobile.domain.bytes

import io.reactivex.Observable

interface BytesRepository {

    fun bytesData(request: BytesRequest): Observable<List<BytesResponse>>
}