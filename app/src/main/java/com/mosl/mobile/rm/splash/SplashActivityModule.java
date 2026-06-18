package com.mosl.mobile.rm.splash;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Binds;
import dagger.Module;

@Module public abstract class SplashActivityModule {
    @Binds abstract AppCompatActivity provideSplashActivity(SplashActivity activity);
}
