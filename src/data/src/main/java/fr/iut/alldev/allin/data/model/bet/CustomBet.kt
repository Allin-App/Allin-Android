package fr.iut.alldev.allin.data.model.bet

import java.time.ZonedDateTime

data class CustomBet(
    override val theme: String,
    override val  phrase: String,
    override val  endRegisterDate: ZonedDateTime,
    override val  endBetDate: ZonedDateTime,
    override val  isPublic: Boolean,
    override val  betStatus: BetStatus,
    val possibleAnswers: Set<String>
) : Bet(
    theme,
    phrase,
    endRegisterDate,
    endBetDate,
    isPublic,
    betStatus
)
