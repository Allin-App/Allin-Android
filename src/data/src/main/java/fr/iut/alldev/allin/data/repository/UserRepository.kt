package fr.iut.alldev.allin.data.repository

import fr.iut.alldev.allin.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow

abstract class UserRepository {

    val currentUser by lazy { MutableStateFlow<User?>(null) }
    val userCoins by lazy { MutableStateFlow<Int?>(null) }

    internal suspend fun updateUser(user: User) {
        currentUser.emit(user)
        userCoins.emit(user.coins)
    }

    abstract suspend fun login(username: String, password: String): String?

    abstract suspend fun login(token: String): String?

    abstract suspend fun register(username: String, email: String, password: String): String?
}