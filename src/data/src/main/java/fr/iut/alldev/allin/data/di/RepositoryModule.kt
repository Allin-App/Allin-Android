package fr.iut.alldev.allin.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.iut.alldev.allin.data.repository.BetRepository
import fr.iut.alldev.allin.data.repository.FriendRepository
import fr.iut.alldev.allin.data.repository.UserRepository
import fr.iut.alldev.allin.data.repository.impl.BetRepositoryImpl
import fr.iut.alldev.allin.data.repository.impl.FriendRepositoryImpl
import fr.iut.alldev.allin.data.repository.impl.UserRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun provideBetRepository(betRepositoryImpl: BetRepositoryImpl): BetRepository

    @Singleton
    @Binds
    abstract fun provideFriendRepository(friendRepositoryImpl: FriendRepositoryImpl): FriendRepository
}