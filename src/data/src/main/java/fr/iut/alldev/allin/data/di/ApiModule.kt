package fr.iut.alldev.allin.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.iut.alldev.allin.data.api.AllInApi
import fr.iut.alldev.allin.data.di.NetworkModule.createRetrofit
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideAllInApi(@AllInUrl url: HttpUrl, okHttpClient: OkHttpClient): AllInApi {
        val retrofit = createRetrofit(url = url, okHttpClient = okHttpClient)
        return retrofit.create(AllInApi::class.java)
    }
}