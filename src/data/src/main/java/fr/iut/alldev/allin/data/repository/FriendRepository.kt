package fr.iut.alldev.allin.data.repository

import fr.iut.alldev.allin.data.model.User

abstract class FriendRepository {
    abstract suspend fun getFriends(token: String): List<User>
    abstract suspend fun add(token: String, username: String)
    abstract suspend fun remove(token: String, username: String)
}