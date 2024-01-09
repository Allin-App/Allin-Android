package fr.iut.alldev.allin.data.repository

import fr.iut.alldev.allin.data.model.User

abstract class UserRepository {
    lateinit var currentUser: User
    abstract suspend fun login(
        username: String,
        password: String
    ): String?

    abstract suspend fun login(token: String): String?

    abstract suspend fun register(
        username: String,
        email: String,
        password: String
    ): String?
}