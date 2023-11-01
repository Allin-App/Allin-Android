package fr.iut.alldev.allin.data.model.bet

import java.time.ZonedDateTime

class BetFactory {
    companion object{
        fun createBet(
            betType: BetType,
            theme: String,
            phrase: String,
            endRegisterDate: ZonedDateTime,
            endBetDate: ZonedDateTime,
            isPublic: Boolean,
            nameTeam1: String = "",
            nameTeam2: String = "",
            possibleAnswers: Set<String> = emptySet()

        ): Bet =
            when(betType){
                BetType.YES_NO -> {
                    YesNoBet(
                        theme = theme,
                        phrase = phrase,
                        endRegisterDate = endRegisterDate,
                        endBetDate = endBetDate,
                        isPublic = isPublic,
                        betStatus = BetStatus.WAITING
                    )
                }
                BetType.MATCH ->  {
                    MatchBet(
                        theme = theme,
                        phrase = phrase,
                        endRegisterDate = endRegisterDate,
                        endBetDate = endBetDate,
                        isPublic = isPublic,
                        betStatus = BetStatus.WAITING,
                        nameTeam1 = nameTeam1,
                        nameTeam2 = nameTeam2
                    )

                }
                BetType.CUSTOM ->  {
                    CustomBet(
                        theme = theme,
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