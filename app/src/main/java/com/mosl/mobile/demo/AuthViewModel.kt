package com.mosl.mobile.demo

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mosl.mobile.domain.demo.DemoRepository
import javax.inject.Inject

class AuthViewModel @Inject constructor(
  val view: DemoView,
  val repository: DemoRepository
) : ViewModel() {

  var name: String? = null
  var email: String? = null
  var password: String? = null



  fun onLoginButtonClick(vi: View) {
    view?.onStarted()
    if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
      view?.onFailure("Invalid email or password")
      return
    }

    Coroutines.main {
      try {
        val authResponse = repository.userLogin(email!!, password!!)
        authResponse?.let {
          view?.onSuccess(MutableLiveData(it))
//                    repository.saveUser(it)
          return@main
        }
        view?.onFailure(authResponse.message!!)
      } catch (e: ApiException) {
        view?.onFailure(e.message!!)
      } catch (e: NoInternetException) {
        view?.onFailure(e.message!!)
      }
    }

  }



}