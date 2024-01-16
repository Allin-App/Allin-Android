package fr.iut.alldev.allin.data.api

import fr.iut.alldev.allin.data.api.interceptors.AllInAPIException
import fr.iut.alldev.allin.data.api.model.CheckUser
import fr.iut.alldev.allin.data.api.model.RequestBet
import fr.iut.alldev.allin.data.api.model.RequestUser
import fr.iut.alldev.allin.data.api.model.ResponseBet
import fr.iut.alldev.allin.data.api.model.ResponseUser
import fr.iut.alldev.allin.data.model.bet.NO_VALUE
import fr.iut.alldev.allin.data.model.bet.YES_VALUE
import java.time.ZonedDateTime
import java.util.UUID

class MockAllInApi : AllInApi {
    override suspend fun login(body: CheckUser): ResponseUser {
        return users.find { it.first.username == body.login && it.second == body.password }?.first
            ?: throw AllInAPIException("Invalid login/password.")
    }

    override suspend fun login(token: String): ResponseUser {
        return users.find { it.first.token == token }?.first
            ?: throw AllInAPIException("Invalid token")
    }

    override suspend fun register(body: RequestUser): ResponseUser {
        val response = ResponseUser(
            id = UUID.randomUUID().toString(),
            username = body.username,
            email = body.email,
            nbCoins = body.nbCoins,
            token = "${body.username} ${users.size}"
        ) to body.password
        users.add(response)
        return response.first
    }

    override suspend fun createBet(token: String, body: RequestBet) {
        mockBets.add(
            ResponseBet(
                id = UUID.randomUUID().toString(),
                theme = body.theme,
                sentenceBet = body.sentenceBet,
                endRegistration = body.endRegistration,
                endBet = body.endBet,
                isPrivate = body.isPrivate,
                response = body.response,
                createdBy = ""
            )
        )
    }

    override suspend fun getAllBets(): List<ResponseBet> = mockBets.toList()
}

private val users = mutableListOf(
    ResponseUser(
        id = "UUID 1",
        username = "User 1",
        email = "john@doe.fr",
        nbCoins = 250,
        token = "token 1"
    ) to "psswrd"
)

private val mockBets = mutableListOf(
    ResponseBet(
        id = "UUID1",
        theme = "Études",
        sentenceBet = "Dave va arriver en retard demain matin ?",
        endRegistration = ZonedDateTime.now().plusDays(3),
        endBet = ZonedDateTime.now().plusDays(4),
        isPrivate = false,
        response = listOf(YES_VALUE, NO_VALUE),
        createdBy = "Armure"
    ),
    ResponseBet(
        id = "UUID2",
        theme = "Études",
        sentenceBet = "Dave va arriver en retard demain matin ?",
        endRegistration = ZonedDateTime.now().plusDays(3),
        endBet = ZonedDateTime.now().plusDays(4),
        isPrivate = false,
        response = listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4"),
        createdBy = "User 2"
    ),
    ResponseBet(
        id = "UUID3",
        theme = "Sport",
        sentenceBet = "Nouveau record du monde ?",
        endRegistration = ZonedDateTime.now().plusDays(3),
        endBet = ZonedDateTime.now().plusDays(4),
        isPrivate = false,
        response = listOf(YES_VALUE, NO_VALUE),
        createdBy = "Armure"
    )
)