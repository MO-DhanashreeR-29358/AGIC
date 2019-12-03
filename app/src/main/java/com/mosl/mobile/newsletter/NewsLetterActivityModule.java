package com.mosl.mobile.newsletter;

import androidx.appcompat.app.AppCompatActivity;

import com.mosl.mobile.data.newsletter.NewsLetterRepositoryImpl;
import com.mosl.mobile.domain.newsletter.NewsLetterRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NewsLetterActivityModule {
    @Binds
    abstract AppCompatActivity provideNewsActivity(NewsLetterActivity activity);

    @Binds
    abstract NewsLetterRepository provideNewsLetterRepository(NewsLetterRepositoryImpl newsLetterRepository);

    @Binds
    abstract NewsLetterView provideNewsLetterView(NewsLetterActivity activity);
}