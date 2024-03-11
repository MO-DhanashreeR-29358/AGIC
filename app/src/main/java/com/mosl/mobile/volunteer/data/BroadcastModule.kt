package com.mosl.mobile.volunteer.data

import android.content.Context
import com.mosl.app.core.network.NetworkReceiver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BroadcastModule {
  @Provides
  @Singleton
  internal fun provideNetworkReceiver(context: Context): NetworkReceiver {
    return NetworkReceiver(NetworkReceiver.getCurrentState(context))
  }
}