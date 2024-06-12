package fr.iut.alldev.allin.data.model.bet

import java.time.ZonedDateTime

sealed class Bet(
    open val id: String,
    open val creator: String,
    open val theme: String,
    open val phrase: String,
    open val endRegisterDate: ZonedDateTime,
    open val endBetDate: ZonedDateTime,
    open val isPublic: Boolean,
    open val betStatus: BetStatus,
    open val totalStakes: Int,
    open val totalParticipants: Int
) {
    abstract fun getBetType(): BetType
    abstract fun getResponses(): List<String>
}