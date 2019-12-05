package com.mosl.mobile.panelpager

import com.mosl.app.core.base.BasePresenter
import com.mosl.mobile.domain.panel.PanelDetailsRepository
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class PanelPresenter @Inject constructor(

   view: PanelView,

   private val repository: PanelDetailsRepository


):BasePresenter<PanelView>(view) {


    override fun start() {
        repository.panelDetailsData().compose(applyUiModel()).subscribe {
            view.renderPanelData(it)
        }.addTo(disposable)
    }
}