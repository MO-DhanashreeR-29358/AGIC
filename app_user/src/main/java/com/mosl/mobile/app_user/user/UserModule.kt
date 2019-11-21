package com.mosl.mobile.app_user.user

import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.login.UserCache
import com.mosl.mobile.login.UserCacheImpl
import com.mosl.mobile.login.UserManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserModule {
  @Provides
   internal fun provideUserManager(userCache: UserCache): UserManager{
    return UserManagerImpl(userCache)
  }

  @Provides @Singleton
  internal fun provideUserCache():UserCache{
    return UserCacheImpl()
  }
}