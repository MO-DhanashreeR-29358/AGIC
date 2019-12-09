package com.mosl.mobile.domain.bytes

data class BytesHeader(
    @JvmField val title: String,
    @JvmField val bytesList: List<BytesData>
)

data class BytesData(
    @JvmField val time: String,
    @JvmField val desc: String
)