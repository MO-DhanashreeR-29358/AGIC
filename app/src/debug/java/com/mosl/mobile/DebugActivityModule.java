package com.mosl.mobile;

import com.mosl.mobile.nav.DebugFragmentResolver;
import com.mosl.navigator.FragmentResolver;
import dagger.Module;
import dagger.Provides;

@Module
public class DebugActivityModule {
    @Provides static FragmentResolver provideFragmentResolver() {
        return DebugFragmentResolver.INSTANCE;
    }
}
