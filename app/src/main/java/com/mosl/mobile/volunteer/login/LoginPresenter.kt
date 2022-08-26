package com.mosl.mobile.volunteer.login

import com.mosl.app.core.base.BasePresenter
import com.mosl.mobile.volunteer.appuser.login.LoginRepository
import io.reactivex.Observable
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    view: LoginView,
    private val loginRepository: LoginRepository
) : BasePresenter<LoginView>(view) {


    override fun start() {
        disposable.add(
            Observable.merge(view.onLoginClicked(), view.onDoneClicked())
                .map {
                    LoginFormInput(
                        view.getUniqueCode(), view.getDeviceId()
                    )
                }
                .switchMap { input ->
                    LoginValidator.validate(input)
                        .flatMap(loginRepository::login)
                        .compose(applyUiModel())
                }.subscribe(view::renderLogin)
        )
    }
}