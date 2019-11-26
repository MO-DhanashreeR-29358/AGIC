package com.mosl.mobile.mock_data

import android.content.Context
import androidx.lifecycle.LiveData
import com.mosl.data.api.MOSLApi
import com.mosl.mobile.demo.AuthResponse
import retrofit2.Response
import retrofit2.mock.MockRetrofit

class MockMoslApiServiceImpl(
    private val mockRetrofit: MockRetrofit,
    private val context: Context) : MOSLApi {
    override suspend fun userLogin(email: String, password: String):AuthResponse{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
