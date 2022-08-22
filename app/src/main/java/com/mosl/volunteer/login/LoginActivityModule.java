package com.mosl.volunteer.login;

import androidx.appcompat.app.AppCompatActivity;

import com.mosl.volunteer.appuser.login.LoginRepository;
import com.mosl.volunteer.appuser.login.LoginRepositoryImpl;
import com.mosl.volunteer.appuser.login.AppUserRepository;
import com.mosl.volunteer.appuser.login.AppUserRepositoryImpl;

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