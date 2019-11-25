package com.mosl.mobile

import com.mosl.mobile.demo.DemoFragmentProvider
import dagger.Module

@Module(includes = [DemoFragmentProvider::class])
abstract class FragmentProvider {
}