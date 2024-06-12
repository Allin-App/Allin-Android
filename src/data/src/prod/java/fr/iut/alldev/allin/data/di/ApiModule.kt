package fr.iut.alldev.allin.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.iut.alldev.allin.data.api.AllInApi
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.HttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Provides
    @Singleton
    @OptIn(ExperimentalSerializationApi::class)
    fun provideAllInApi(@AllInUrl url: HttpUrl, okHttpClient: OkHttpClient): AllInApi = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(url)
        .build()
        .create<AllInApi>()
}