package fr.iut.alldev.allin.data.api.model

import androidx.annotation.Keep
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.NO_VALUE
import fr.iut.alldev.allin.data.model.bet.YES_VALUE
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import fr.iut.alldev.allin.data.serialization.SimpleDateSerializer
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Keep
@Serializable
data class ResponseBet(
    val id: String?,
    val theme: String,
    val sentenceBet: String,
    @Serializable(SimpleDateSerializer::class) val endRegistration: ZonedDateTime,
    @Serializable(SimpleDateSerializer::class) var endBet: ZonedDateTime,
    var isPrivate: Boolean,
    var response: List<String>,
    val createdBy: String
) {
    fun toBet(): Bet {
        if (response.toSet() == setOf(YES_VALUE, NO_VALUE)) {
            return YesNoBet(
                theme = theme,
                phrase = sentenceBet,
                endRegisterDate = endRegistration,
                endBetDate = endBet,
                isPublic = !isPrivate,
                betStatus = BetStatus.Waiting,
                creator = createdBy
            )
        } else {
            return CustomBet(
                theme = theme,
                phrase = sentenceBet,
                endRegisterDate = endRegistration,
                endBetDate = endBet,
                isPublic = !isPrivate,
                betStatus = BetStatus.Waiting,
                creator = createdBy,
                possibleAnswers = response
            )
        }
    }
}

@Keep
@Serializable
data class RequestBet(
    val theme: String,
    val sentenceBet: String,
    @Serializable(SimpleDateSerializer::class) val endRegistration: ZonedDateTime,
    @Serializable(SimpleDateSerializer::class) var endBet: ZonedDateTime,
    var isPrivate: Boolean,
    var response: List<String>
)
