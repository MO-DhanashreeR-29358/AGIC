package com.mosl.mobile.login;

import androidx.appcompat.app.AppCompatActivity;

import com.mosl.mobile.appuser.login.LoginRepository;
import com.mosl.mobile.appuser.login.LoginRepositoryImpl;
import com.mosl.mobile.appuser.login.AppUserRepository;
import com.mosl.mobile.appuser.login.AppUserRepositoryImpl;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoginActivityModule {
    @Binds
    abstract AppCompatActivity provideSplashActivity(LoginActivity activity);

    @Binds abstract LoginRepository provideLoginRepository(LoginRepositoryImpl userManager);

    @Binds abstract AppUserRepository provideAppUserRepository(AppUserRepositoryImpl appUserRepository);

    @Binds abstract LoginView provideCustomerLoginView(LoginActivity activity);
}