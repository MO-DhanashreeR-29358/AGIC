package com.mosl.volunteer.bytes

import com.mosl.volunteer.data.bytes.BytesRepositoryImpl
import com.mosl.volunteer.domain.bytes.BytesRepository
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