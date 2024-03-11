package com.mosl.mobile.volunteer.newsletter

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.volunteer.domain.newsletter.NewsLetterResponseInfo
import io.reactivex.Observable

interface NewsLetterView : BaseView {
    fun onSkipClicked(): Observable<Unit>
    fun renderSkip(uiModel: UiModel<Unit>)
    fun renderBrochureData(uiModel: UiModel<NewsLetterResponseInfo>)
    fun hideProgress()
    fun showProgress()

}