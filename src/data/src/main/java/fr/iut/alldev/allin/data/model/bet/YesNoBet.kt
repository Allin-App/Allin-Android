package fr.iut.alldev.allin.data.model.bet

import java.time.ZonedDateTime

data class YesNoBet(
    override val theme: String,
    override val phrase: String,
    override val endRegisterDate: ZonedDateTime,
    override val endBetDate: ZonedDateTime,
    override val isPublic: Boolean,
    override val betStatus: BetStatus
) : Bet(
    theme,
    phrase,
    endRegisterDate,
    endBetDate,
    isPublic,
    betStatus
)