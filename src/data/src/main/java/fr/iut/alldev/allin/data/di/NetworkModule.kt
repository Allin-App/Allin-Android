package fr.iut.alldev.allin.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import javax.inject.Qualifier

internal val json by lazy {
    Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AllInUrl

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @AllInUrl
    @Provides
    fun provideUrl(): HttpUrl = "https://codefirst.iut.uca.fr/containers/AllDev-api/".toHttpUrl()

}
