package com.mosl.mobile.newsletter

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import io.reactivex.Observable

interface NewsLetterView : BaseView {
    fun onSkipClicked(): Observable<Unit>
    fun renderSkip(uiModel: UiModel<Unit>)
}