package fr.iut.alldev.allin.data.model.bet

import java.time.ZonedDateTime

data class YesNoBet(
    override val id: Int? = null,
    override val creator: String,
    override val theme: String,
    override val phrase: String,
    override val endRegisterDate: ZonedDateTime,
    override val endBetDate: ZonedDateTime,
    override val isPublic: Boolean,
    override val betStatus: BetStatus,
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
    override fun getResponses(): List<String> = listOf("Yes", "No")
}