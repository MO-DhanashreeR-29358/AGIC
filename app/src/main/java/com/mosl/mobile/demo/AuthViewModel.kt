package com.mosl.mobile.demo

import android.util.Base64
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mosl.mobile.data.util.Encryptor
import com.mosl.mobile.domain.demo.DemoRepository
import com.mosl.mobile.domain.demo.LoginInfoD
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
    try {
      Coroutines.io {

        val liveDataResponse: LiveData<UserResponseMapper>
        liveDataResponse = liveData {
          val response = repository.userLogin(
            LoginInfoD(
              "client_credentials",
              encrypt1(password!!),
              email!!,
              "255.177.148.251"
            )
          )
          emit(response)

        }
        withContext(Dispatchers.Main) {
          view.onSuccess(liveDataResponse)
        }

      }
    } catch (e: Exception) {

      view.onFailure(e.message ?: "An Error Occurred")

    }


}



}

fun encrypt1(input: String): String {
  return encrypt2(input, System.currentTimeMillis().toString())
}

fun encrypt2(input: String, currentTime: String): String {
  val encryptor = Encryptor()
  return try {
    val part1 = Base64.encodeToString(
      System.currentTimeMillis().toString().toByteArray(charset("UTF-8")),
      Base64.DEFAULT
    )
    val part2 = Base64.encodeToString(
      input.toByteArray(charset("UTF-8")),
      Base64.DEFAULT
    )
    val part3 = Base64.encodeToString(
      currentTime.toByteArray(charset("UTF-8")),
      Base64.DEFAULT
    )
    encryptor.encrypt(String.format("%s.%s.%s", part1, part2, part3), "mosl")
  } catch (e: java.lang.Exception) {
    return ""
  }
}
