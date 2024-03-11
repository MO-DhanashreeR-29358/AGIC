package com.mosl.mobile.volunteer.data.newsletter

import com.mosl.data.api.newsletter.NewsLetterResponseItem
import com.mosl.mobile.volunteer.domain.newsletter.NewsLetterResponseInfo
import com.mosl.mobile.volunteer.domain.newsletter.NewsLetterResponseItemInfo

object NewsLetterMapper {

    fun transformNewsLetter(
        responseList: List<NewsLetterResponseItem>?,
        isActive: Boolean
    ): NewsLetterResponseInfo {

        val responseLisaat: List<NewsLetterResponseItemInfo> = responseList!!.map {
            NewsLetterResponseItemInfo(
                IMAGENAME = it.imageName,
                IMAGEPATH = it.imagePath,
                Title = it.title,
                HeadLines = it.headLines
            )
        }

        return NewsLetterResponseInfo(
            Data = responseLisaat,
            IsActive = isActive
        )
    }

}

