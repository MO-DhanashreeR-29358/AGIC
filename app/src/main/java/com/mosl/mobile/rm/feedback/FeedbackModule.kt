package com.mosl.mobile.rm.feedback

import com.mosl.mobile.data.feedback.FeedbackRepositoryImpl
import com.mosl.mobile.domain.feedback.FeedbackRepository
import dagger.Binds
import dagger.Module

@Module
abstract class FeedbackModule {

    @Binds
    abstract fun provideFeedback(fragment: FeedbackFragment): FeedbackContract

    @Binds
    abstract fun provideRepository(repository: FeedbackRepositoryImpl): FeedbackRepository


}