package com.mosl.mobile.data.newsletter

import com.mosl.data.api.AGICApi
import com.mosl.mobile.domain.newsletter.NewsLetterRepository
import com.mosl.mobile.domain.newsletter.NewsLetterResponseInfo
import io.reactivex.Observable
import javax.inject.Inject

class NewsLetterRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi
) : NewsLetterRepository {

    private val mapper = NewsLetterMapper

    override fun brochureData(): Observable<NewsLetterResponseInfo> {

        return agicApi.getBrochureData().map {
            mapper.transformNewsLetter(it.data,it.IsActive) }
    }
}