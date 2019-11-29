package com.mosl.mobile.panel

import com.mosl.app.core.base.BaseView

interface PanelChildContract {
    interface View : BaseView {
        fun renderPage()
    }
}