package com.mosl.mobile.domain.panel

import io.reactivex.Observable

interface PanelDetailsRepository {

    fun panelDetailsData():Observable<PanelResponse>
}