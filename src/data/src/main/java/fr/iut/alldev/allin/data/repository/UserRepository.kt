package fr.iut.alldev.allin.data.repository

import fr.iut.alldev.allin.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class UserRepository {

    internal val _currentUser by lazy { MutableStateFlow<User?>(null) }
    val currentUser by lazy { _currentUser.asStateFlow() }

    suspend fun resetCurrentUser() {
        _currentUser.emit(null)
    }

    suspend fun updateCurrentUserCoins(value: Int) {
        currentUser.value?.let { user ->
            _currentUser.emit(
                user.copy(
                    coins = value
                )
            )
        }
    }

    abstract suspend fun login(username: String, password: String): String?

    abstract suspend fun login(token: String): String?

    abstract suspend fun register(username: String, email: String, password: String): String?
    abstract suspend fun dailyGift(token: String): Int
}