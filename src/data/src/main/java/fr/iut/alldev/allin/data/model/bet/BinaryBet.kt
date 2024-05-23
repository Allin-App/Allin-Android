package fr.iut.alldev.allin.data.model.bet

import java.time.ZonedDateTime

const val YES_VALUE = "Yes"
const val NO_VALUE = "No"

data class BinaryBet(
    override val id: String,
    override val creator: String,
    override val theme: String,
    override val phrase: String,
    override val endRegisterDate: ZonedDateTime,
    override val endBetDate: ZonedDateTime,
    override val isPublic: Boolean,
    override val betStatus: BetStatus
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
    override fun getBetType() = BetType.BINARY
    override fun getResponses(): List<String> = listOf(YES_VALUE, NO_VALUE)
}