package com.mosl.mobile.bytes

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import io.reactivex.Observable

interface BytesContract : BaseView {

    fun swipeRefresh(): Observable<Unit>
    fun renderData(uiModel:UiModel<List<String>>)


}