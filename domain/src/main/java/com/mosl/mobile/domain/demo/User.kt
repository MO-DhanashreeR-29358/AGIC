package com.mosl.mobile.demo




data class User(
    @JvmField  var id: Int? = null,
    @JvmField  var name: String? = null,
    @JvmField var email: String? = null,
    @JvmField  var password: String? = null,
    @JvmField  var email_verified_at: String? = null,
    @JvmField var created_at: String? = null,
    @JvmField var updated_at: String? = null
)