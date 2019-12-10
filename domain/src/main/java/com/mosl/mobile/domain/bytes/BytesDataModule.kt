package com.mosl.mobile.domain.bytes

data class BytesItemModel(
    @JvmField  var byte:String,
    @JvmField  var entryDate:String
)

data class BytesData(
    @JvmField val headerTime: String,
    @JvmField val itemList: List<BytesItemModel>
)