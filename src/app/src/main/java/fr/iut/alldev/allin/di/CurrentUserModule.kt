package fr.iut.alldev.allin.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.iut.alldev.allin.data.repository.UserRepository
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AllInCurrentUser

@Module
@InstallIn(SingletonComponent::class)
internal object CurrentUserModule {
    @AllInCurrentUser
    @Provides
    fun provideUser(
        userRepository: UserRepository
    ) = userRepository.currentUser
}