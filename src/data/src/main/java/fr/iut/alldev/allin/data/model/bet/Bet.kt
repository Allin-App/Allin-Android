package fr.iut.alldev.allin.data.model.bet

import java.time.ZonedDateTime

abstract class Bet(
    open val theme: String,
    open val phrase: String,
    open val endRegisterDate: ZonedDateTime,
    open val endBetDate: ZonedDateTime,
    open val isPublic: Boolean,
    open val betStatus: BetStatus,
)