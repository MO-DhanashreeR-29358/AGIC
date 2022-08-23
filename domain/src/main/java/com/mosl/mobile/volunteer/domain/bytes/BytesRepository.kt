package com.mosl.mobile.volunteer.domain.bytes

import io.reactivex.Observable

interface BytesRepository {

    fun bytesData(): Observable<List<BytesResponse>>
}