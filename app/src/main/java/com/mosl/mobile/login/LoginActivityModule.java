package com.mosl.mobile.login;

import androidx.appcompat.app.AppCompatActivity;

import com.mosl.mobile.app_user.login.LoginRepository;
import com.mosl.mobile.app_user.login.LoginRepositoryImpl;
import com.mosl.mobile.user.login.AppUserRepository;
import com.mosl.mobile.user.login.AppUserRepositoryImpl;

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