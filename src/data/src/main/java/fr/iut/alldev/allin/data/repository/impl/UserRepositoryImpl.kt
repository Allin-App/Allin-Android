package fr.iut.alldev.allin.data.repository.impl

import fr.iut.alldev.allin.data.api.AllInApi
import fr.iut.alldev.allin.data.api.model.CheckUser
import fr.iut.alldev.allin.data.api.model.ResponseUser
import fr.iut.alldev.allin.data.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: AllInApi,
) : UserRepository() {
    override suspend fun login(username: String, password: String) {
        api.login(
            CheckUser(
                username = username,
                password = password
            )
        )
    }

    override suspend fun register(username: String, email: String, password: String) {
        api.register(
            ResponseUser(
                username = username,
                email = email,
                password = password,
                nbCoins = 0
            )
        )
    }

}