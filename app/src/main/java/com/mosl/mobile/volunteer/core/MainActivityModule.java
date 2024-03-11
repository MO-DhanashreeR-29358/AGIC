package com.mosl.mobile.volunteer.core;

import com.mosl.mobile.volunteer.menu.AppFragmentResolver;
import com.mosl.navigator.FragmentResolver;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {
    @Provides static FragmentResolver provideFragmentResolver() {
        return AppFragmentResolver.INSTANCE;
    }
}
