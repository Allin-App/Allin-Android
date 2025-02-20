package fr.iut.alldev.allin.data.api

import fr.iut.alldev.allin.data.api.model.CheckUser
import fr.iut.alldev.allin.data.api.model.RequestBet
import fr.iut.alldev.allin.data.api.model.RequestBetFilters
import fr.iut.alldev.allin.data.api.model.RequestFriend
import fr.iut.alldev.allin.data.api.model.RequestParticipation
import fr.iut.alldev.allin.data.api.model.RequestUser
import fr.iut.alldev.allin.data.api.model.ResponseBet
import fr.iut.alldev.allin.data.api.model.ResponseBetAnswerDetail
import fr.iut.alldev.allin.data.api.model.ResponseBetDetail
import fr.iut.alldev.allin.data.api.model.ResponseBetResult
import fr.iut.alldev.allin.data.api.model.ResponseBetResultDetail
import fr.iut.alldev.allin.data.api.model.ResponseParticipation
import fr.iut.alldev.allin.data.api.model.ResponseUser
import fr.iut.alldev.allin.data.model.FriendStatus
import fr.iut.alldev.allin.data.model.bet.BetFilter
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.BetType
import fr.iut.alldev.allin.data.model.bet.NO_VALUE
import fr.iut.alldev.allin.data.model.bet.YES_VALUE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import java.time.Duration
import java.time.ZonedDateTime
import java.util.UUID

class MockAllInApiException(override val message: String?) : Exception()

class MockAllInApi : AllInApi {
    init {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(10_000)
                updateBets(ZonedDateTime.now())
            }
        }
    }

    private fun updateBets(date: ZonedDateTime) {
        mockBets.forEachIndexed { idx, bet ->
            if (bet.status != BetStatus.CANCELLED && bet.status != BetStatus.FINISHED) {
                if (date >= bet.endRegistration) {
                    if (date >= bet.endBet) {
                        mockBets[idx] = bet.copy(status = BetStatus.CLOSING)
                    } else {
                        mockBets[idx] = bet.copy(status = BetStatus.WAITING)
                    }
                }
            }
        }
    }

    private fun getUserFromToken(token: String) =
        mockUsers.find { it.first.token == token.removePrefix("Bearer ") }

    private fun getAnswerDetails(
        bet: ResponseBet,
        participations: List<ResponseParticipation>
    ): List<ResponseBetAnswerDetail> {
        return bet.response.map { response ->
            val responseParticipations = participations.filter { it.answer == response }
            ResponseBetAnswerDetail(
                response = response,
                totalStakes = responseParticipations.sumOf { it.stake },
                totalParticipants = responseParticipations.size,
                highestStake = responseParticipations.maxOfOrNull { it.stake } ?: 0,
                odds = if (participations.isEmpty()) 0.0f else responseParticipations.size / participations.size.toFloat()
            )
        }
    }

    private fun getFriendStatus(userId: String, withId: String): FriendStatus {
        return mockFriends.filter {
            it.first == userId && it.second == withId
        }.let {
            if (it.isEmpty()) FriendStatus.NOT_FRIEND
            else mockFriends.filter {
                it.second == userId && it.first == withId
            }.let {
                if (it.isEmpty()) FriendStatus.REQUESTED
                else FriendStatus.FRIEND
            }
        }
    }

    override suspend fun login(body: CheckUser): ResponseUser {
        return mockUsers.find { it.first.username == body.login && it.second == body.password }?.first
            ?: throw MockAllInApiException("Invalid login/password.")
    }

    override suspend fun login(token: String): ResponseUser {
        return getUserFromToken(token)?.first
            ?: throw MockAllInApiException("Invalid token")
    }

    override suspend fun register(body: RequestUser): ResponseUser {
        val response = ResponseUser(
            id = UUID.randomUUID().toString(),
            username = body.username,
            email = body.email,
            nbCoins = 500,
            token = "${body.username} ${mockUsers.size}",
            nbBets = 0,
            nbFriends = 0,
            bestWin = 0
        ) to body.password
        mockUsers.add(response)
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
                type = BetType.BINARY,
                status = BetStatus.IN_PROGRESS,
                createdBy = getUserFromToken(token)?.first?.username ?: ""
            )
        )
    }

    override suspend fun dailyGift(token: String): Int {
        getUserFromToken(token) ?: throw MockAllInApiException("Invalid login/password.")
        if (mockGifts[token] == null) {
            mockGifts[token] = ZonedDateTime.now().minusDays(1)
        }

        val duration = Duration.between(mockGifts[token], ZonedDateTime.now())
        if (duration.toDays() >= 1) {
            val amount = (10..150).random()
            mockGifts[token] = ZonedDateTime.now()


            mockUsers.replaceAll {
                if (it.first.token == token) {
                    it.copy(
                        first = it.first.copy(
                            nbCoins = it.first.nbCoins + amount
                        )
                    )
                } else it
            }
            return amount
        } else throw MockAllInApiException("Gift already taken today")
    }

    override suspend fun setImage(token: String, base64: RequestBody) {
        val user = getUserFromToken(token) ?: throw MockAllInApiException("Invalid login/password.")
    }

    override suspend fun getFriends(token: String): List<ResponseUser> {
        val user = getUserFromToken(token) ?: throw MockAllInApiException("Invalid login/password.")
        return mockFriends
            .filter { it.first == user.first.id }
            .mapNotNull { itUser ->
                mockUsers.find { usr -> usr.first.id == itUser.second }
                    ?.first
                    ?.copy(
                        friendStatus = getFriendStatus(userId = user.first.id, withId = itUser.second)
                    )
            }
    }

    override suspend fun getFriendRequests(token: String): List<ResponseUser> {
        val user = getUserFromToken(token) ?: throw MockAllInApiException("Invalid login/password.")
        return mockFriends
            .filter {
                (it.second == user.first.id) &&
                        mockFriends.none { it2 ->
                            it2.first == user.first.id && it2.second == it.first
                        }
            }
            .mapNotNull { itUser ->
                mockUsers.find { usr -> usr.first.id == itUser.first }
                    ?.first
                    ?.copy(friendStatus = FriendStatus.NOT_FRIEND)
            }
    }

    override suspend fun addFriend(token: String, request: RequestFriend) {
        val user = getUserFromToken(token) ?: throw MockAllInApiException("Invalid login/password.")
        val requestUser =
            mockUsers.find { it.first.username == request.username } ?: throw MockAllInApiException("Requested user not found")
        mockFriends.add(user.first.id to requestUser.first.id)
    }

    override suspend fun deleteFriend(token: String, request: RequestFriend) {
        val user = getUserFromToken(token) ?: throw MockAllInApiException("Invalid login/password.")
        val requestUser =
            mockUsers.find { it.first.username == request.username } ?: throw MockAllInApiException("Requested user not found")
        mockFriends.remove(user.first.id to requestUser.first.id)
    }

    override suspend fun searchFriend(token: String, search: String): List<ResponseUser> {
        val user = getUserFromToken(token) ?: throw MockAllInApiException("Invalid login/password.")
        return mockUsers.filter { it.first.username.contains(search, ignoreCase = true) }
            .map { itUser ->
                itUser.first.copy(
                    friendStatus = getFriendStatus(userId = user.first.id, withId = itUser.first.id)
                )
            }
    }

    override suspend fun getAllBets(token: String, body: RequestBetFilters): List<ResponseBet> {
        getUserFromToken(token) ?: throw MockAllInApiException("Invalid login/password.")
        val filters = body.filters
        return when {
            filters.isEmpty() -> mockBets

            filters.size == 1 -> {
                val filter = filters[0]

                when (filter) {
                    BetFilter.PUBLIC -> mockBets.filter { !it.isPrivate }
                    BetFilter.INVITATION -> mockBets.filter { it.isPrivate }
                    BetFilter.FINISHED -> mockBets.filter { it.status == BetStatus.FINISHED }
                    BetFilter.IN_PROGRESS -> mockBets.filter {
                        it.status in listOf(BetStatus.IN_PROGRESS, BetStatus.WAITING, BetStatus.CLOSING)
                    }
                }.map { it }
            }

            else -> {
                mockBets.filter { bet ->
                    val public = (BetFilter.PUBLIC in filters) && !bet.isPrivate
                    val invitation = (BetFilter.INVITATION in filters) && bet.isPrivate
                    val finished =
                        (BetFilter.FINISHED in filters) and ((bet.status == BetStatus.FINISHED) or (bet.status == BetStatus.CANCELLED))
                    val inProgress = (BetFilter.IN_PROGRESS in filters) and (bet.status in listOf(
                        BetStatus.IN_PROGRESS,
                        BetStatus.WAITING,
                        BetStatus.CLOSING
                    ))

                    (public || invitation) && (finished or inProgress)
                }.map { it }
            }
        }
    }

    override suspend fun getPopularBet(token: String): ResponseBet? {
        return mockBets.firstOrNull()
    }

    override suspend fun getToConfirm(token: String): List<ResponseBetDetail> {
        val user = getUserFromToken(token) ?: throw MockAllInApiException("Invalid login/password.")
        return mockBets.filter {
            it.createdBy == user.first.username && it.status == BetStatus.CLOSING
        }.map { bet ->
            val betParticipations = mockParticipations.filter { it.betId == bet.id }
            val userParticipation = betParticipations.find { it.username == user.first.username }

            ResponseBetDetail(
                bet = bet,
                answers = getAnswerDetails(bet, betParticipations),
                participations = betParticipations,
                userParticipation = userParticipation
            )
        }
    }

    override suspend fun confirmBet(token: String, id: String, value: RequestBody) {
        getUserFromToken(token) ?: throw MockAllInApiException("Invalid login/password.")
        val bet = mockBets.find { it.id == id } ?: throw MockAllInApiException("Unauthorized")
        mockResults.add(
            ResponseBetResult(
                betId = id,
                result = value.toString()
            )
        )
        mockBets[mockBets.indexOf(bet)] = bet.copy(status = BetStatus.FINISHED)
    }

    override suspend fun getBet(token: String, id: String): ResponseBetDetail {
        val bet = mockBets.find { it.id == id } ?: throw MockAllInApiException("Bet not found")
        val user = getUserFromToken(token) ?: throw MockAllInApiException("Invalid login/password.")
        val betParticipations = mockParticipations.filter { it.betId == bet.id }
        val userParticipation = betParticipations.find { it.username == user.first.username }
        val wonParticipation = if (bet.status == BetStatus.FINISHED) {
            val result = mockResults.find { it.betId == bet.id }
            betParticipations.filter { it.answer == result?.result }.maxByOrNull { it.stake }
        } else null

        return ResponseBetDetail(
            bet = bet,
            answers = getAnswerDetails(bet, betParticipations),
            participations = betParticipations,
            userParticipation = userParticipation,
            wonParticipation = wonParticipation
        )
    }

    override suspend fun getBetCurrent(token: String): List<ResponseBetDetail> {
        val user = getUserFromToken(token) ?: throw MockAllInApiException("Invalid login/password.")
        val betParticipations = mockParticipations.filter { it.username == user.first.username }
        return betParticipations.map { p ->
            getBet(token, p.betId)
        }.filter {
            it.bet.status !in listOf(BetStatus.FINISHED, BetStatus.CANCELLED)
        }
    }

    override suspend fun getBetHistory(token: String): List<ResponseBetResultDetail> {
        val user = getUserFromToken(token) ?: throw MockAllInApiException("Invalid login/password.")
        val betParticipations = mockParticipations.filter { it.username == user.first.username }
        return betParticipations.map { p ->
            getBet(token, p.betId)
        }.filter {
            it.bet.status in listOf(BetStatus.FINISHED, BetStatus.CANCELLED)
        }.mapNotNull { bet ->
            val participation = bet.userParticipation ?: return@mapNotNull null
            val result = mockResults.find { it.betId == bet.bet.id } ?: return@mapNotNull null
            ResponseBetResultDetail(
                betResult = result,
                bet = bet.bet,
                participation = participation,
                amount = participation.stake, // TODO won amount
                won = participation.answer == result.result
            )
        }
    }

    override suspend fun getWon(token: String): List<ResponseBetResultDetail> {
        return getBetHistory(token).filter {
            val isWon = it.won && mockWon[token]?.contains(it.bet.id ?: "") != true
            if (isWon) {
                mockWon[token]?.add(it.bet.id ?: "") ?: run {
                    mockWon[token] = mutableListOf(it.bet.id ?: "")
                }

                mockUsers.replaceAll { user ->
                    if (user.first.token == token) {
                        user.copy(
                            first = user.first.copy(
                                nbCoins = user.first.nbCoins + it.amount
                            )
                        )
                    } else user
                }

                true
            } else false

        }
    }

    override suspend fun participateToBet(token: String, body: RequestParticipation) {
        getUserFromToken(token)?.let {
            mockParticipations.add(
                ResponseParticipation(
                    id = "",
                    userId = "",
                    betId = body.betId,
                    username = it.first.username,
                    answer = body.answer,
                    stake = body.stake

                )
            )
        } ?: throw MockAllInApiException("Invalid token")
    }

    companion object {
        private val mockUsers = mutableListOf(
            ResponseUser(
                id = "UUID 1",
                username = "User 1",
                email = "john@doe.fr",
                nbCoins = 250,
                token = "token 1",
                nbBets = 0,
                nbFriends = 0,
                bestWin = 0
            ) to "12345",
            ResponseUser(
                id = "UUID 2",
                username = "User 2",
                email = "john@doe.fr",
                nbCoins = 250,
                token = "token 2",
                nbBets = 0,
                nbFriends = 0,
                bestWin = 0
            ) to "12345",
            ResponseUser(
                id = "UUID 3",
                username = "User 3",
                email = "john@doe.fr",
                nbCoins = 250,
                token = "token 3",
                nbBets = 0,
                nbFriends = 0,
                bestWin = 0
            ) to "12345",
            ResponseUser(
                id = "UUID 4",
                username = "User 4",
                email = "john@doe.fr",
                nbCoins = 250,
                token = "token 4",
                nbBets = 0,
                nbFriends = 0,
                bestWin = 0
            ) to "12345",
            ResponseUser(
                id = "UUID 5",
                username = "User 5",
                email = "john@doe.fr",
                nbCoins = 250,
                token = "token 5",
                nbBets = 0,
                nbFriends = 0,
                bestWin = 0
            ) to "12345",
            ResponseUser(
                id = "UUID 6",
                username = "User 6",
                email = "john@doe.fr",
                nbCoins = 250,
                token = "token 6",
                nbBets = 0,
                nbFriends = 0,
                bestWin = 0
            ) to "12345",
            ResponseUser(
                id = "UUID 7",
                username = "User 7",
                email = "john@doe.fr",
                nbCoins = 250,
                token = "token 7",
                nbBets = 0,
                nbFriends = 0,
                bestWin = 0
            ) to "12345"
        )

        private val mockParticipations = mutableListOf(
            ResponseParticipation(
                id = "",
                userId = "",
                betId = "UUID1",
                username = mockUsers[1].first.username,
                answer = NO_VALUE,
                stake = 1500
            ),
            ResponseParticipation(
                id = "",
                userId = "",
                betId = "UUID1",
                username = mockUsers[6].first.username,
                answer = YES_VALUE,
                stake = 222
            ),
            ResponseParticipation(
                id = "",
                userId = "",
                betId = "UUID2",
                username = mockUsers[0].first.username,
                answer = "Answer 1",
                stake = 200
            ),
            ResponseParticipation(
                id = "",
                userId = "",
                betId = "UUID2",
                username = mockUsers[2].first.username,
                answer = "Answer 1",
                stake = 200
            ),
            ResponseParticipation(
                id = "",
                userId = "",
                betId = "UUID2",
                username = mockUsers[6].first.username,
                answer = "Answer 1",
                stake = 50
            ),
            ResponseParticipation(
                id = "",
                userId = "",
                betId = "UUID3",
                username = mockUsers[1].first.username,
                answer = "The Monarchs",
                stake = 420
            )
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
                createdBy = "Armure",
                type = BetType.BINARY,
                status = BetStatus.IN_PROGRESS,
            ),
            ResponseBet(
                id = "UUID2",
                theme = "Études",
                sentenceBet = "Quoi ?",
                endRegistration = ZonedDateTime.now().plusDays(3),
                endBet = ZonedDateTime.now().plusDays(4),
                isPrivate = false,
                response = listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4"),
                createdBy = "User 2",
                type = BetType.CUSTOM,
                status = BetStatus.IN_PROGRESS,
            ),
            ResponseBet(
                id = "UUID3",
                theme = "Sport",
                sentenceBet = "Quelle équipe va gagner ?",
                endRegistration = ZonedDateTime.now().plusDays(3),
                endBet = ZonedDateTime.now().plusDays(2),
                isPrivate = false,
                response = listOf("The Monarchs", "Climate Change"),
                createdBy = "User 1",
                type = BetType.MATCH,
                status = BetStatus.IN_PROGRESS,
            )
        )

        private val mockResults by lazy { mutableListOf<ResponseBetResult>() }

        private val mockWon by lazy { mutableMapOf<String, MutableList<String>>() }

        private val mockGifts by lazy { mutableMapOf<String, ZonedDateTime>() }

        private val mockFriends by lazy {
            mutableSetOf(
                "UUID 1" to "UUID 2",
                "UUID 2" to "UUID 1",
                "UUID 1" to "UUID 3"
            )
        }
    }

}
