package com.mosl.app.core.network

/**
 * Created by lalkishore on 6/8/2016.
 */
data class NetworkChangeEvent(
    @JvmField val isConnected: Boolean,
    @JvmField val type: String
)
