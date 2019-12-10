package com.mosl.mobile.bytes

import com.mosl.mobile.data.bytes.BytesRepositoryImpl
import com.mosl.mobile.domain.bytes.BytesRepository
import dagger.Module
import dagger.Provides

@Module
class BytesModule {

    @Provides
    internal fun ProvideBytesFragment(fragment: BytesFragment): BytesContract {
        return fragment
    }

    @Provides
    internal fun ProvideBytesRepository(repository: BytesRepositoryImpl):BytesRepository{
        return repository
    }
}