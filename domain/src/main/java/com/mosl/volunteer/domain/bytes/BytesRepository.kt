package com.mosl.volunteer.domain.bytes

import io.reactivex.Observable

interface BytesRepository {

    fun bytesData(): Observable<List<BytesResponse>>
}