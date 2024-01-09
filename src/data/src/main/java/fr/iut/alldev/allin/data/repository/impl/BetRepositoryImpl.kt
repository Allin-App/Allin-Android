package fr.iut.alldev.allin.data.repository.impl

import fr.iut.alldev.allin.data.api.AllInApi
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.BetFinishedStatus
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import fr.iut.alldev.allin.data.repository.BetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.ZonedDateTime
import javax.inject.Inject

class BetRepositoryImpl @Inject constructor(
    private val api: AllInApi
) : BetRepository() {
    override suspend fun createBet(bet: Bet) {
        api.createBet(bet.toResponseBet())
    }

    override suspend fun getHistory(): Flow<List<Bet>> {
        // TODO
        return flowOf(
            listOf(
                YesNoBet(
                    creator = "Lucas",
                    theme = "Theme",
                    phrase = "Bet phrase 1",
                    endRegisterDate = ZonedDateTime.now().minusDays(4),
                    endBetDate = ZonedDateTime.now().minusDays(2),
                    isPublic = false,
                    betStatus = BetStatus.Finished(BetFinishedStatus.WON)
                ),
                YesNoBet(
                    creator = "Lucas",
                    theme = "Theme",
                    phrase = "Bet phrase 2",
                    endRegisterDate = ZonedDateTime.now().minusDays(3),
                    endBetDate = ZonedDateTime.now().minusDays(1),
                    isPublic = true,
                    betStatus = BetStatus.Finished(BetFinishedStatus.LOST)
                ),
                YesNoBet(
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
                    creator = "Lucas",
                    theme = "Theme",
                    phrase = "Bet phrase 1",
                    endRegisterDate = ZonedDateTime.now().plusDays(5),
                    endBetDate = ZonedDateTime.now().plusDays(7),
                    isPublic = false,
                    betStatus = BetStatus.InProgress
                ),
                YesNoBet(
                    creator = "Lucas",
                    theme = "Theme",
                    phrase = "Bet phrase 2",
                    endRegisterDate = ZonedDateTime.now().plusDays(1),
                    endBetDate = ZonedDateTime.now().plusDays(2),
                    isPublic = true,
                    betStatus = BetStatus.InProgress
                ),
                YesNoBet(
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

}