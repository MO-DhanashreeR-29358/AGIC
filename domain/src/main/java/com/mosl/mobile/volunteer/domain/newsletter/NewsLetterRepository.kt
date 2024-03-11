package com.mosl.mobile.volunteer.domain.newsletter

import io.reactivex.Observable

interface NewsLetterRepository {
    fun brochureData(): Observable<NewsLetterResponseInfo>
}