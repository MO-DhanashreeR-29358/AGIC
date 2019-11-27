package com.mosl.mobile.newsletter

import com.mosl.app.core.base.BaseView

interface NewsLetterContract {
    interface View : BaseView {
        fun renderPage()
    }
}