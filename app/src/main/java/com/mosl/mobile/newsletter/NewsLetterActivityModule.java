package com.mosl.mobile.newsletter;

import androidx.appcompat.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NewsLetterActivityModule {
    @Binds
    abstract AppCompatActivity provideNewsActivity(NewsLetterActivity activity);
}