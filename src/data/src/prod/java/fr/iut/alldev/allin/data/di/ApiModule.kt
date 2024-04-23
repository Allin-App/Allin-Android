package fr.iut.alldev.allin.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.iut.alldev.allin.data.api.AllInApi
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Provides
    @Singleton
    fun provideAllInApi(@AllInUrl url: HttpUrl, okHttpClient: OkHttpClient): AllInApi {
        val retrofit = NetworkModule.createRetrofit(url = url, okHttpClient = okHttpClient)
        return retrofit.create(AllInApi::class.java)
    }
}