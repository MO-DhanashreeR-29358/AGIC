package com.mosl.mobile.panel

import com.mosl.app.core.base.BaseView

interface PanelContract {
    interface View : BaseView {
        fun renderPage()
    }
}