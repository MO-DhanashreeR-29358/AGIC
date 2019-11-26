package com.mosl.mobile.mock_data

import android.content.Context
import com.mosl.data.api.MOSLApi
import com.mosl.mobile.demo.UserResponse
import retrofit2.mock.MockRetrofit

class MockMoslApiServiceImpl(
    private val mockRetrofit: MockRetrofit,
    private val context: Context) : MOSLApi {
    override suspend fun userLogin(email: String, password: String):UserResponse{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
