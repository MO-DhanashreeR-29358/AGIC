package com.mosl.mobile.volunteer.feedback

import com.mosl.mobile.volunteer.data.feedback.FeedbackRepositoryImpl
import com.mosl.mobile.volunteer.domain.feedback.FeedbackRepository
import dagger.Binds
import dagger.Module

@Module
abstract class FeedbackModule {

    @Binds
    abstract fun provideFeedback(fragment: FeedbackFragment): FeedbackContract

    @Binds
    abstract fun provideRepository(repository: FeedbackRepositoryImpl): FeedbackRepository


}