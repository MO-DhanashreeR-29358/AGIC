package com.mosl.mobile.rm;

import com.mosl.mobile.rm.nav.DebugFragmentResolver;
import com.mosl.navigator.FragmentResolver;
import dagger.Module;
import dagger.Provides;

@Module
public class DebugActivityModule {
    @Provides static FragmentResolver provideFragmentResolver() {
        return DebugFragmentResolver.INSTANCE;
    }
}
