package brawijaya.example.tuteedaftarkelas.data.di

import brawijaya.example.tuteedaftarkelas.data.repository.KelasRepository
import brawijaya.example.tuteedaftarkelas.data.repository.KelasRepositoryFirebase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindKelasRepository(
        impl: KelasRepositoryFirebase
    ): KelasRepository
}
