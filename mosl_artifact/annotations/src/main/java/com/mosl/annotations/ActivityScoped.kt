package com.mosl.annotations

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

@Retention(RUNTIME)
@Scope
annotation class ActivityScoped