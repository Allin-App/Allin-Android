package fr.iut.alldev.allin.test.mock

import fr.iut.alldev.allin.data.model.bet.vo.BetDetail

object Bets {
    val bets by lazy {
        listOf<BetDetail>(
            /*            YesNoBet(
                            theme = "Theme",
                            phrase = "Phrase",
                            endRegisterDate = ZonedDateTime.now(),
                            endBetDate = ZonedDateTime.now(),
                            isPublic = true,
                            betStatus = BetStatus.IN_PROGRESS,
                            creator = "creator",
                            id = ""
                        ),
                        MatchBet(
                            theme = "Theme",
                            phrase = "Phrase",
                            endRegisterDate = ZonedDateTime.now(),
                            endBetDate = ZonedDateTime.now(),
                            isPublic = true,
                            betStatus = BetStatus.IN_PROGRESS,
                            nameTeam1 = "Team_1",
                            nameTeam2 = "Team_2",
                            creator = "creator",
                            id = ""
                        ),
                        CustomBet(
                            theme = "Theme",
                            phrase = "Phrase",
                            endRegisterDate = ZonedDateTime.now(),
                            endBetDate = ZonedDateTime.now(),
                            isPublic = true,
                            betStatus = BetStatus.IN_PROGRESS,
                            creator = "creator",
                            possibleAnswers = listOf(
                                "Answer 1",
                                "Answer 2",
                                "Answer 3",
                                "Answer 4"
                            ),
                            id = ""
                        )*/
        )
    }
}