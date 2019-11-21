package com.mosl.mobile.mock_data

import android.content.Context
import com.mosl.data.api.MOSLApi
import retrofit2.mock.MockRetrofit

class MockMoslApiServiceImpl(
    private val mockRetrofit: MockRetrofit,
    private val context: Context) : MOSLApi
