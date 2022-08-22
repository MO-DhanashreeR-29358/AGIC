package com.mosl.volunteer.core;

import com.mosl.volunteer.menu.AppFragmentResolver;
import com.mosl.navigator.FragmentResolver;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {
    @Provides static FragmentResolver provideFragmentResolver() {
        return AppFragmentResolver.INSTANCE;
    }
}
