package com.mosl.volunteer.appuser

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Retention(RUNTIME)
@Qualifier
annotation class UserPreference

@Retention(RUNTIME)
@Qualifier
annotation class RxUserPreference