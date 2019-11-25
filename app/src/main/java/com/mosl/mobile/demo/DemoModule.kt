package com.mosl.mobile.demo

import androidx.lifecycle.ViewModelProvider
import com.mosl.mobile.data.demo.DemoRepositoryImpl
import com.mosl.mobile.domain.demo.DemoRepository
import dagger.Module
import dagger.Provides

@Module
class DemoModule {

  @Provides
  internal fun ProvidePortfolioFragment(fragment: DemoFragment): DemoView {
    return fragment
  }

  @Provides
  internal fun provideAuthViewModelFactory(factory: AuthViewModelFactory): ViewModelProvider.NewInstanceFactory {
    return factory
  }

  @Provides
  internal fun provideRepository(factory: DemoRepositoryImpl): DemoRepository {
    return factory
  }



}