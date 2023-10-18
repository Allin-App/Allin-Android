package fr.iut.alldev.allin.data.model.bet

import java.time.ZonedDateTime

class YesNoBet(
    theme: String,
    phrase: String,
    endRegisterDate: ZonedDateTime,
    endBetDate: ZonedDateTime,
    isPublic: Boolean,
    betStatus: BetStatus
) : Bet(
    theme,
    phrase,
    endRegisterDate,
    endBetDate,
    isPublic,
    betStatus
)