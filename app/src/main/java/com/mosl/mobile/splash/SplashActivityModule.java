package com.mosl.mobile.splash;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Binds;
import dagger.Module;

@Module public abstract class SplashActivityModule {
    @Binds abstract AppCompatActivity provideSplashActivity(SplashActivity activity);
}
