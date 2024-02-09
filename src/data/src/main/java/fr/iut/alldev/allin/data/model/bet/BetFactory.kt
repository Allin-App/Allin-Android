package fr.iut.alldev.allin.data.model.bet

import java.time.ZonedDateTime

class BetFactory {
    companion object {
        fun createBet(
            id: String,
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
                BetType.BINARY -> {
                    YesNoBet(
                        id = id,
                        theme = theme,
                        creator = creator,
                        phrase = phrase,
                        endRegisterDate = endRegisterDate,
                        endBetDate = endBetDate,
                        isPublic = isPublic,
                        betStatus = BetStatus.WAITING
                    )
                }

                BetType.MATCH -> {
                    MatchBet(
                        id = id,
                        theme = theme,
                        creator = creator,
                        phrase = phrase,
                        endRegisterDate = endRegisterDate,
                        endBetDate = endBetDate,
                        isPublic = isPublic,
                        betStatus = BetStatus.WAITING,
                        nameTeam1 = nameTeam1,
                        nameTeam2 = nameTeam2
                    )

                }

                BetType.CUSTOM -> {
                    CustomBet(
                        id = id,
                        theme = theme,
                        creator = creator,
                        phrase = phrase,
                        endRegisterDate = endRegisterDate,
                        endBetDate = endBetDate,
                        isPublic = isPublic,
                        betStatus = BetStatus.WAITING,
                        possibleAnswers = possibleAnswers
                    )
                }
            }
    }
}