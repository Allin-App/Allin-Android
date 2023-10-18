package fr.iut.alldev.allin.data.model.bet

import java.time.ZonedDateTime

class MatchBet(
    theme: String,
    phrase: String,
    endRegisterDate: ZonedDateTime,
    endBetDate: ZonedDateTime,
    isPublic: Boolean,
    betStatus: BetStatus,
    val nameTeam1: String,
    val nameTeam2: String
) : Bet(
    theme,
    phrase,
    endRegisterDate,
    endBetDate,
    isPublic,
    betStatus
)