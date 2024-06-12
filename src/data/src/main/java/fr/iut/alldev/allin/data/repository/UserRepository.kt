package fr.iut.alldev.allin.data.repository

import fr.iut.alldev.allin.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class UserRepository {

    internal val currentUser by lazy { MutableStateFlow<User?>(null) }
    val currentUserState get() = currentUser.asStateFlow()

    suspend fun resetCurrentUser() {
        currentUser.emit(null)
    }

    suspend fun updateCurrentUserCoins(value: Int) {
        currentUserState.value?.let { user ->
            currentUser.emit(
                user.copy(
                    coins = value
                )
            )
        }
    }

    suspend fun updateCurrentUserImage(value: String?) {
        currentUserState.value?.let { user ->
            currentUser.emit(
                user.copy(
                    image = null
                )
            )
            currentUser.emit(
                user.copy(
                    image = value
                )
            )
        }
    }

    abstract suspend fun login(username: String, password: String): String?
    abstract suspend fun login(token: String): String?
    abstract suspend fun register(username: String, email: String, password: String): String?
    abstract suspend fun dailyGift(token: String): Int
    abstract suspend fun setImage(token: String, base64: String)
    abstract fun getImageUrl(id: String): String
}