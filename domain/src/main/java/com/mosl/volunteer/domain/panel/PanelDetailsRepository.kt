package com.mosl.volunteer.domain.panel

import io.reactivex.Observable

interface PanelDetailsRepository {

    fun panelDetailsData():Observable<PanelResponse>
}