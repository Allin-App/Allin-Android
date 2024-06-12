package fr.iut.alldev.allin.data.repository.impl

import fr.iut.alldev.allin.data.api.AllInApi
import fr.iut.alldev.allin.data.api.AllInApi.Companion.asRequestBody
import fr.iut.alldev.allin.data.api.AllInApi.Companion.formatBearerToken
import fr.iut.alldev.allin.data.api.model.CheckUser
import fr.iut.alldev.allin.data.api.model.RequestUser
import fr.iut.alldev.allin.data.di.AllInUrl
import fr.iut.alldev.allin.data.repository.UserRepository
import okhttp3.HttpUrl
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: AllInApi,
    @AllInUrl private val apiUrl: HttpUrl
) : UserRepository() {

    override fun getImageUrl(id: String) = "$apiUrl/users/images/$id"

    override suspend fun login(username: String, password: String): String? {
        val response = api.login(
            CheckUser(
                login = username,
                password = password
            )
        )
        currentUser.emit(response.toUser())
        return response.token
    }

    override suspend fun login(token: String): String? {
        val response = api.login(token = token.formatBearerToken())
        currentUser.emit(response.toUser())
        return response.token
    }

    override suspend fun register(username: String, email: String, password: String): String? {
        val response = api.register(
            RequestUser(
                username = username,
                email = email,
                password = password
            )
        )
        currentUser.emit(response.toUser())
        return response.token
    }

    override suspend fun dailyGift(token: String): Int =
        api.dailyGift(token.formatBearerToken())

    override suspend fun setImage(token: String, base64: String) {
        api.setImage(token = token.formatBearerToken(), base64 = base64.asRequestBody())
    }
}