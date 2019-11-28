package com.mosl.mobile.app_user

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Retention(RUNTIME)
@Qualifier
annotation class UserPreference

@Retention(RUNTIME)
@Qualifier
annotation class RxUserPreference