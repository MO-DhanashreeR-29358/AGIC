package com.mosl.mobile

import com.mosl.mobile.demo.DemoMVVMFragmentProvider
import dagger.Module

@Module(includes = [DemoMVVMFragmentProvider::class])
abstract class FragmentProvider {
}