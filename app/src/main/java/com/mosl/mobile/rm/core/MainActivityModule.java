package com.mosl.mobile.rm.core;

import com.mosl.mobile.rm.menu.AppFragmentResolver;
import com.mosl.navigator.FragmentResolver;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {
    @Provides static FragmentResolver provideFragmentResolver() {
        return AppFragmentResolver.INSTANCE;
    }
}
