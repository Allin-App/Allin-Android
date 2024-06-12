package fr.iut.alldev.allin.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.iut.alldev.allin.data.api.AllInApi
import fr.iut.alldev.allin.data.api.MockAllInApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Provides
    @Singleton
    internal fun provideAllInApi(): AllInApi = MockAllInApi()
}