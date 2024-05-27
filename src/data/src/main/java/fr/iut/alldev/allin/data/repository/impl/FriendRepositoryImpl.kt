package fr.iut.alldev.allin.data.repository.impl

import fr.iut.alldev.allin.data.api.AllInApi
import fr.iut.alldev.allin.data.api.AllInApi.Companion.formatBearerToken
import fr.iut.alldev.allin.data.api.model.RequestFriend
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.data.repository.FriendRepository
import javax.inject.Inject

class FriendRepositoryImpl @Inject constructor(
    private val api: AllInApi
) : FriendRepository() {
    override suspend fun getFriends(token: String): List<User> {
        return api.getFriends(token.formatBearerToken()).map { it.toUser() }
    }

    override suspend fun add(token: String, username: String) {
        return api.addFriend(
            token = token.formatBearerToken(),
            request = RequestFriend(username)
        )
    }

    override suspend fun remove(token: String, username: String) {
        return api.deleteFriend(
            token = token.formatBearerToken(),
            request = RequestFriend(username)
        )
    }

    override suspend fun searchNew(token: String, search: String): List<User> {
        return api.searchFriend(
            token = token.formatBearerToken(),
            search = search
        ).map { it.toUser() }
    }
}