package com.mosl.mobile.appuser

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Retention(RUNTIME)
@Qualifier
annotation class UserPreference

@Retention(RUNTIME)
@Qualifier
annotation class RxUserPreference