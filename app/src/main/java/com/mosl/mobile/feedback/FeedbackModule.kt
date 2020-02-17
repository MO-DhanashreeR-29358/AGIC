package com.mosl.mobile.feedback

import com.mosl.mobile.data.notification.NotificationRepositoryImpl
import com.mosl.mobile.domain.notification.NotificationRepository
import dagger.Module
import dagger.Provides

@Module
class FeedbackModule {

    @Provides
    internal fun provideFeedback(fragment: FeedbackFragment): FeedbackContract {
        return fragment
    }

}