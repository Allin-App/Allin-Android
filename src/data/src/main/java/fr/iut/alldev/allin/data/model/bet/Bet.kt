package fr.iut.alldev.allin.data.model.bet

import fr.iut.alldev.allin.data.api.model.RequestBet
import java.time.ZonedDateTime

abstract class Bet(
    open val id: Int? = null,
    open val creator: String,
    open val theme: String,
    open val phrase: String,
    open val endRegisterDate: ZonedDateTime,
    open val endBetDate: ZonedDateTime,
    open val isPublic: Boolean,
    open val betStatus: BetStatus,
) {
    abstract fun getResponses(): List<String>
    fun toRequestBet(): RequestBet {
        return RequestBet(
            theme = theme,
            sentenceBet = phrase,
            endRegistration = endRegisterDate,
            endBet = endBetDate,
            isPrivate = !isPublic,
            response = getResponses(),
            createdBy = creator
        )
    }
}