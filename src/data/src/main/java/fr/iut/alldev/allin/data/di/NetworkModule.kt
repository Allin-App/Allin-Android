package fr.iut.alldev.allin.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Qualifier

internal val json by lazy {
    Json {
        ignoreUnknownKeys = true
        encodeDefaults = false
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

    fun createRetrofit(url: HttpUrl, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(url)
            .build()
}