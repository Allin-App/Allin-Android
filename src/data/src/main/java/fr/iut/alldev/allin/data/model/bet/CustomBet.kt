package fr.iut.alldev.allin.data.model.bet

import java.time.ZonedDateTime

data class CustomBet(
    override val id: String,
    override val creator: String,
    override val theme: String,
    override val phrase: String,
    override val endRegisterDate: ZonedDateTime,
    override val endBetDate: ZonedDateTime,
    override val isPublic: Boolean,
    override val betStatus: BetStatus,
    override val totalStakes: Int,
    override val totalParticipants: Int,
    val possibleAnswers: List<String>
) : Bet(
    id,
    creator,
    theme,
    phrase,
    endRegisterDate,
    endBetDate,
    isPublic,
    betStatus,
    totalStakes,
    totalParticipants
) {
    override fun getBetType() = BetType.CUSTOM

    override fun getResponses(): List<String> = possibleAnswers
}
