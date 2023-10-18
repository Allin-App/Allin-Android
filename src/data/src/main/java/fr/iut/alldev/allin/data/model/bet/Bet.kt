package fr.iut.alldev.allin.data.model.bet

import java.time.ZonedDateTime

abstract class Bet(
    val theme: String,
    val phrase: String,
    val endRegisterDate: ZonedDateTime,
    val endBetDate: ZonedDateTime,
    val isPublic: Boolean,
    val betStatus: BetStatus,
)