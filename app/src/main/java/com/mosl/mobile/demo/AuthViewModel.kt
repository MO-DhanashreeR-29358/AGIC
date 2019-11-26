package com.mosl.mobile.demo

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mosl.mobile.domain.demo.DemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthViewModel @Inject constructor(
  val view: DemoView,
  val repository: DemoRepository
) : ViewModel() {

  var name: String? = null
  var email: String? = null
  var password: String? = null
  fun onLoginButtonClick(vi: View) {
    view.onStarted()
    if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
      view.onError("Invalid email or password")
      return
    }

    Coroutines.io {
      try {

        val liveDataResponse: LiveData<UserResponseMapper>
        liveDataResponse = liveData {
          val response = repository.userLogin(email!!, password!!)
          emit(response)

        }
        withContext(Dispatchers.Main) {
          view.onSuccess(liveDataResponse)
        }

      } catch (e: Exception) {
        view.onFailure(e.message ?: "An Error Occurred")
      }
    }

  }

}

