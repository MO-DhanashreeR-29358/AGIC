package com.mosl.mobile.newsletter

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.newsletter.NewsLetterResponseInfo
import com.mosl.mobile.domain.newsletter.NewsLetterResponseItemInfo
import io.reactivex.Observable

interface NewsLetterView : BaseView {
    fun onSkipClicked(): Observable<Unit>
    fun renderSkip(uiModel: UiModel<Unit>)
    fun renderBrochureData(uiModel: UiModel<NewsLetterResponseInfo>)
    fun hideProgress()
    fun showProgress()

}