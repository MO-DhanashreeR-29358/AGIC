package com.mosl.mobile.mock_data

import android.content.Context
import com.mosl.data.api.demo.LoginInfo
import com.mosl.data.api.AGICApi
import com.mosl.data.api.login.LoginInfoRequest
import com.mosl.data.api.login.LoginInfoResponse
import com.mosl.mobile.demo.UserResponse
import io.reactivex.Observable
import retrofit2.mock.MockRetrofit

class MockMoslApiServiceImpl(
    private val mockRetrofit: MockRetrofit,
    private val context: Context) : AGICApi {
    override suspend fun demoLogin(loginInfo: LoginInfo):UserResponse{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun userLogin(request: LoginInfoRequest): Observable<LoginInfoResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
