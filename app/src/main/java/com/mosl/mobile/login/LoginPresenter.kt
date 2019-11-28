package com.mosl.mobile.login

import com.mosl.app.core.base.BasePresenter
import com.mosl.mobile.app_user.login.LoginRepository
import io.reactivex.Observable
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    view: LoginView,
    val loginRepository: LoginRepository
) : BasePresenter<LoginView>(view) {


    override fun start() {
        disposable.add(
            Observable.merge(view.onLoginClicked(), view.onDoneClicked())
                .map<LoginFormInput> {
                    LoginFormInput(
                        view.getUniqueCode(), view.getDeviceId()
                    )
                }
                .switchMap { input ->
                    LoginValidator.validate(input)
                        .flatMap {
                            loginRepository.login(it)
                        }.compose(applyUiModel())
                }.subscribe {
                    view.renderLogin(it)

                }
        )
    }

}