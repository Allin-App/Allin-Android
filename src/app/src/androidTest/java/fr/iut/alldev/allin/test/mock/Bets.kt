package fr.iut.alldev.allin.test.mock

import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import java.time.ZonedDateTime

object Bets {
    val bets by lazy {
        listOf(
            YesNoBet(
                theme = "Theme",
                phrase = "Phrase",
                endRegisterDate = ZonedDateTime.now(),
                endBetDate = ZonedDateTime.now(),
                isPublic = true,
                betStatus = BetStatus.InProgress
            ),
            MatchBet(
                theme = "Theme",
                phrase = "Phrase",
                endRegisterDate = ZonedDateTime.now(),
                endBetDate = ZonedDateTime.now(),
                isPublic = true,
                betStatus = BetStatus.InProgress,
                nameTeam1 = "Team_1",
                nameTeam2 = "Team_2"
            ),
        )
    }
}