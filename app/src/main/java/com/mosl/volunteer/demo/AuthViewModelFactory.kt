package com.mosl.volunteer.demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mosl.volunteer.domain.demo.DemoRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory @Inject constructor(
    val view: DemoView,
     val repository: DemoRepository

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(view,repository) as T
    }
}