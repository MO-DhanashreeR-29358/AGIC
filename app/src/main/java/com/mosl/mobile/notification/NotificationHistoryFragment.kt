package com.mosl.mobile.notification

import android.os.Bundle
import android.view.View
import com.mosl.commons.data.UiModel
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentNotificationHistoryBinding

class NotificationHistoryFragment : MOSLCommonFragment(),NotificationContract {

    lateinit var binding : FragmentNotificationHistoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotificationHistoryBinding.bind(view)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_notification_history
    }

    override fun render(uiModel: UiModel<String>) {

    }

}