package fr.iut.alldev.allin.data.repository

abstract class UserRepository {
    abstract suspend fun login(
        username: String,
        password: String
    )
    abstract suspend fun register(
        username: String,
        email: String,
        password: String
    )
}