package com.mosl.mobile.data.newsletter

import com.mosl.data.api.newsletter.NewsLetterResponse
import com.mosl.data.api.newsletter.NewsLetterResponseItem
import com.mosl.mobile.domain.newsletter.NewsLetterResponseInfo
import com.mosl.mobile.domain.newsletter.NewsLetterResponseItemInfo

object NewsLetterMapper{

//    fun transformNewsLetter(masterResponse: List<NewsLetterResponse>): List<NewsLetterResponseInfo> {
//
//        return NewsLetterResponseInfo(
//            IsActive = masterResponse.IsActive,
//            Data = masterResponse.newsLetterResponseItem?.map {
//                transformLeadCountData(
//                    it
//                )
//                    .ma
//            }
//        )
//    }

    fun transformNewsLetter(responseList: List<NewsLetterResponseItem>?,isActive:String): List<NewsLetterResponseItemInfo> {
        if (responseList.isNullOrEmpty()) return emptyList()

        return responseList.map {
            NewsLetterResponseItemInfo(
                IMAGENAME = it.imageName,
                IMAGEPATH = it.imagePath,
                Title = it.title,
                HeadLines = it.headLines
            )
        }
    }


    fun transformLeadCountData(leadCountItem: NewsLetterResponseItem?): NewsLetterResponseItemInfo {
        return NewsLetterResponseItemInfo(
            IMAGENAME = leadCountItem?.imageName,
            IMAGEPATH = leadCountItem?.imagePath,
            Title = leadCountItem?.title,
            HeadLines = leadCountItem?.headLines
        )
    }
}

