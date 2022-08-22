package com.mosl.volunteer.feedback

import com.mosl.volunteer.data.feedback.FeedbackRepositoryImpl
import com.mosl.volunteer.domain.feedback.FeedbackRepository
import dagger.Binds
import dagger.Module

@Module
abstract class FeedbackModule {

    @Binds
    abstract fun provideFeedback(fragment: FeedbackFragment): FeedbackContract

    @Binds
    abstract fun provideRepository(repository: FeedbackRepositoryImpl): FeedbackRepository


}