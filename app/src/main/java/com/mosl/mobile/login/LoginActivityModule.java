package com.mosl.mobile.login;

import androidx.appcompat.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoginActivityModule {
    @Binds
    abstract AppCompatActivity provideSplashActivity(LoginActivity activity);
}