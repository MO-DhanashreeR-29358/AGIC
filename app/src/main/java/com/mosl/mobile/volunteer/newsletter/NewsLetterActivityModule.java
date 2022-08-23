package com.mosl.mobile.volunteer.newsletter;

import androidx.appcompat.app.AppCompatActivity;

import com.mosl.mobile.volunteer.data.newsletter.NewsLetterRepositoryImpl;
import com.mosl.mobile.volunteer.domain.newsletter.NewsLetterRepository;

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