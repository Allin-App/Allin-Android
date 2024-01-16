package fr.iut.alldev.allin.data.model.bet

import java.time.ZonedDateTime

class BetFactory {
    companion object {
        fun createBet(
            betType: BetType,
            creator: String,
            theme: String,
            phrase: String,
            endRegisterDate: ZonedDateTime,
            endBetDate: ZonedDateTime,
            isPublic: Boolean,
            nameTeam1: String = "",
            nameTeam2: String = "",
            possibleAnswers: List<String> = emptyList(),

            ): Bet =
            when (betType) {
                BetType.YES_NO -> {
                    YesNoBet(
                        theme = theme,
                        creator = creator,
                        phrase = phrase,
                        endRegisterDate = endRegisterDate,
                        endBetDate = endBetDate,
                        isPublic = isPublic,
                        betStatus = BetStatus.Waiting
                    )
                }

                BetType.MATCH -> {
                    MatchBet(
                        theme = theme,
                        creator = creator,
                        phrase = phrase,
                        endRegisterDate = endRegisterDate,
                        endBetDate = endBetDate,
                        isPublic = isPublic,
                        betStatus = BetStatus.Waiting,
                        nameTeam1 = nameTeam1,
                        nameTeam2 = nameTeam2
                    )

                }

                BetType.CUSTOM -> {
                    CustomBet(
                        theme = theme,
                        creator = creator,
                        phrase = phrase,
                        endRegisterDate = endRegisterDate,
                        endBetDate = endBetDate,
                        isPublic = isPublic,
                        betStatus = BetStatus.Waiting,
                        possibleAnswers = possibleAnswers
                    )
                }
            }
    }
}