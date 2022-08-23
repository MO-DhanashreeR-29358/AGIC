package com.mosl.mobile.volunteer;

import com.mosl.mobile.volunteer.nav.DebugFragmentResolver;
import com.mosl.navigator.FragmentResolver;
import dagger.Module;
import dagger.Provides;

@Module
public class DebugActivityModule {
    @Provides static FragmentResolver provideFragmentResolver() {
        return DebugFragmentResolver.INSTANCE;
    }
}
