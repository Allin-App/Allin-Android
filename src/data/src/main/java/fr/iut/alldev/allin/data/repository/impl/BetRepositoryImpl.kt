package fr.iut.alldev.allin.data.repository.impl

import fr.iut.alldev.allin.data.api.AllInApi
import fr.iut.alldev.allin.data.api.AllInApi.Companion.formatBearerToken
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.BetFinishedStatus
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.Participation
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail
import fr.iut.alldev.allin.data.repository.BetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.ZonedDateTime
import javax.inject.Inject

class BetRepositoryImpl @Inject constructor(
    private val api: AllInApi
) : BetRepository() {
    override suspend fun createBet(bet: Bet, token: String) {
        api.createBet(
            token = token.formatBearerToken(),
            body = bet.toRequestBet()
        )
    }

    override suspend fun getHistory(): Flow<List<Bet>> {
        return flowOf(
            listOf(
                YesNoBet(
                    id = "1",
                    creator = "Lucas",
                    theme = "Theme",
                    phrase = "Bet phrase 1",
                    endRegisterDate = ZonedDateTime.now().minusDays(4),
                    endBetDate = ZonedDateTime.now().minusDays(2),
                    isPublic = false,
                    betStatus = BetStatus.Finished(BetFinishedStatus.WON)
                ),
                YesNoBet(
                    id = "2",
                    creator = "Lucas",
                    theme = "Theme",
                    phrase = "Bet phrase 2",
                    endRegisterDate = ZonedDateTime.now().minusDays(3),
                    endBetDate = ZonedDateTime.now().minusDays(1),
                    isPublic = true,
                    betStatus = BetStatus.Finished(BetFinishedStatus.LOST)
                ),
                YesNoBet(
                    id = "3",
                    creator = "Lucas",
                    theme = "Theme",
                    phrase = "Bet phrase 3",
                    endRegisterDate = ZonedDateTime.now().minusDays(15),
                    endBetDate = ZonedDateTime.now().minusDays(7),
                    isPublic = false,
                    betStatus = BetStatus.Finished(BetFinishedStatus.LOST)
                )
            )
        )
    }

    override suspend fun getCurrentBets(): Flow<List<Bet>> {
        // TODO
        return flowOf(
            listOf(
                YesNoBet(
                    id = "1",
                    creator = "Lucas",
                    theme = "Theme",
                    phrase = "Bet phrase 1",
                    endRegisterDate = ZonedDateTime.now().plusDays(5),
                    endBetDate = ZonedDateTime.now().plusDays(7),
                    isPublic = false,
                    betStatus = BetStatus.InProgress
                ),
                YesNoBet(
                    id = "2",
                    creator = "Lucas",
                    theme = "Theme",
                    phrase = "Bet phrase 2",
                    endRegisterDate = ZonedDateTime.now().plusDays(1),
                    endBetDate = ZonedDateTime.now().plusDays(2),
                    isPublic = true,
                    betStatus = BetStatus.InProgress
                ),
                YesNoBet(
                    id = "3",
                    creator = "Lucas",
                    theme = "Theme",
                    phrase = "Bet phrase 3",
                    endRegisterDate = ZonedDateTime.now().plusDays(3),
                    endBetDate = ZonedDateTime.now().plusDays(4),
                    isPublic = false,
                    betStatus = BetStatus.InProgress
                )
            )
        )
    }

    override suspend fun getBet(id: String, token: String): BetDetail {
        return api.getBet(
            token = token.formatBearerToken(),
            id = id
        ).toBetDetail()
    }

    override suspend fun participateToBet(participation: Participation, token: String) {
        api.participateToBet(token = token.formatBearerToken(), body = participation.toRequestParticipation())
    }

    override suspend fun getAllBets(): List<Bet> {
        return api.getAllBets().map { it.toBet() }
    }

}