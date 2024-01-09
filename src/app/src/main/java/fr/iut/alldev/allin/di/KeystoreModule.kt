package fr.iut.alldev.allin.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.iut.alldev.allin.keystore.AllInKeystoreManager
import fr.iut.alldev.allin.keystore.impl.AllInKeystoreManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class KeystoreModule {
    @Singleton
    @Binds
    abstract fun provideKeystoreManager(allInKeystoreManagerImpl: AllInKeystoreManagerImpl): AllInKeystoreManager
}