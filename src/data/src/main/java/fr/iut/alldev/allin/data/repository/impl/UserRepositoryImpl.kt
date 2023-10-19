package fr.iut.alldev.allin.data.repository.impl

import fr.iut.alldev.allin.data.api.AllInApi
import fr.iut.alldev.allin.data.api.model.CheckUser
import fr.iut.alldev.allin.data.api.model.RequestUser
import fr.iut.alldev.allin.data.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: AllInApi,
) : UserRepository() {

    override suspend fun login(username: String, password: String) {
        currentUser = api.login(
            CheckUser(
                login = username,
                password = password
            )
        ).toUser()
    }


    override suspend fun register(username: String, email: String, password: String) {
        currentUser = api.register(
            RequestUser(
                username = username,
                email = email,
                password = password,
                nbCoins = 0
            )
        ).toUser()

    }
}