package fr.iut.alldev.allin.data.model.bet

import java.time.ZonedDateTime

data class MatchBet(
    override val id: Int? = null,
    override val creator: String,
    override val theme: String,
    override val phrase: String,
    override val endRegisterDate: ZonedDateTime,
    override val endBetDate: ZonedDateTime,
    override val isPublic: Boolean,
    override val betStatus: BetStatus,
    val nameTeam1: String,
    val nameTeam2: String,
) : Bet(
    id,
    creator,
    theme,
    phrase,
    endRegisterDate,
    endBetDate,
    isPublic,
    betStatus
) {
    override fun getResponses(): List<String> = listOf(nameTeam1, nameTeam2)

}