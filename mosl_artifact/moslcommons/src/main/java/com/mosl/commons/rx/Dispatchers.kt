package com.mosl.commons.rx

import io.reactivex.Scheduler

data class Dispatchers(
    @JvmField val io: Scheduler,
    @JvmField val computation: Scheduler,
    @JvmField val main: Scheduler,
    @JvmField val db: Scheduler
)