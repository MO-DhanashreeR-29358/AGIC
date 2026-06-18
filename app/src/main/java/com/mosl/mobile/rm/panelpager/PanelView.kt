package com.mosl.mobile.rm.panelpager

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.panel.PanelResponse

interface PanelView : BaseView {

    fun renderPanelData(uiModel:UiModel<PanelResponse>)

}