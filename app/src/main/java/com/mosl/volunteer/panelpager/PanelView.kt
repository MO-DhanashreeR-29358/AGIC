package com.mosl.volunteer.panelpager

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.volunteer.domain.panel.PanelResponse

interface PanelView : BaseView {

    fun renderPanelData(uiModel:UiModel<PanelResponse>)

}