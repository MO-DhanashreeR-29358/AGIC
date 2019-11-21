package com.mosl.mobile.core;

import com.mosl.mobile.menu.AppFragmentResolver;
import com.mosl.navigator.FragmentResolver;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {
    @Provides static FragmentResolver provideFragmentResolver() {
        return AppFragmentResolver.INSTANCE;
    }
}
