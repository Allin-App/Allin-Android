package fr.iut.alldev.allin.data.api.model

import androidx.annotation.Keep
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.BetFilter
import fr.iut.alldev.allin.data.model.bet.BetResult
import fr.iut.alldev.allin.data.model.bet.BetResultDetail
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.BetType
import fr.iut.alldev.allin.data.model.bet.BinaryBet
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.NO_VALUE
import fr.iut.alldev.allin.data.model.bet.YES_VALUE
import fr.iut.alldev.allin.data.model.bet.vo.BetAnswerDetail
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail
import fr.iut.alldev.allin.data.serialization.ZonedDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Keep
@Serializable
data class ResponseBet(
    val id: String?,
    val theme: String,
    val type: BetType,
    val status: BetStatus,
    val sentenceBet: String,
    @Serializable(ZonedDateTimeSerializer::class) val endRegistration: ZonedDateTime,
    @Serializable(ZonedDateTimeSerializer::class) var endBet: ZonedDateTime,
    var isPrivate: Boolean,
    var response: List<String>,
    val createdBy: String,
    var popularityscore: Int = 0,
    val totalStakes: Int = 0,
    val totalParticipants: Int = 0
) {
    fun toBet(): Bet = when {
        response.toSet() == setOf(YES_VALUE, NO_VALUE) -> {
            BinaryBet(
                id = id ?: "",
                theme = theme,
                phrase = sentenceBet,
                endRegisterDate = endRegistration,
                endBetDate = endBet,
                isPublic = !isPrivate,
                betStatus = status,
                creator = createdBy,
                totalStakes = totalStakes,
                totalParticipants = totalParticipants
            )
        }

        type == BetType.MATCH -> {
            MatchBet(
                id = id ?: "",
                theme = theme,
                phrase = sentenceBet,
                endRegisterDate = endRegistration,
                endBetDate = endBet,
                isPublic = !isPrivate,
                betStatus = status,
                creator = createdBy,
                nameTeam1 = response.firstOrNull() ?: "",
                nameTeam2 = response.lastOrNull() ?: "",
                totalStakes = totalStakes,
                totalParticipants = totalParticipants
            )
        }

        else -> {
            CustomBet(
                id = id ?: "",
                theme = theme,
                phrase = sentenceBet,
                endRegisterDate = endRegistration,
                endBetDate = endBet,
                isPublic = !isPrivate,
                betStatus = status,
                creator = createdBy,
                possibleAnswers = response,
                totalStakes = totalStakes,
                totalParticipants = totalParticipants
            )
        }
    }

}

@Keep
@Serializable
data class RequestBet(
    val id: String = "",
    val theme: String,
    val type: BetType,
    val sentenceBet: String,
    @Serializable(ZonedDateTimeSerializer::class) val endRegistration: ZonedDateTime,
    @Serializable(ZonedDateTimeSerializer::class) var endBet: ZonedDateTime,
    var isPrivate: Boolean,
    var response: List<String>
)

@Keep
@Serializable
data class ResponseBetAnswerDetail(
    val response: String,
    val totalStakes: Int,
    val totalParticipants: Int,
    val highestStake: Int,
    val odds: Float
) {
    fun toAnswerDetail() =
        BetAnswerDetail(
            response = response,
            totalStakes = totalStakes,
            totalParticipants = totalParticipants,
            highestStake = highestStake,
            odds = odds
        )
}

@Keep
@Serializable
data class ResponseBetDetail(
    val bet: ResponseBet,
    val answers: List<ResponseBetAnswerDetail>,
    val participations: List<ResponseParticipation>,
    val userParticipation: ResponseParticipation? = null,
    val wonParticipation: ResponseParticipation? = null
) {
    fun toBetDetail() =
        BetDetail(
            bet = bet.toBet(),
            answers = answers.map { it.toAnswerDetail() },
            participations = participations.map { it.toParticipation() },
            userParticipation = userParticipation?.toParticipation(),
            wonParticipation = wonParticipation?.toParticipation()
        )
}

@Serializable
data class ResponseBetResult(
    val betId: String,
    val result: String
) {
    fun toBetResult() =
        BetResult(
            betId = betId,
            result = result
        )
}

@Serializable
data class ResponseBetResultDetail(
    val betResult: ResponseBetResult,
    val bet: ResponseBet,
    val participation: ResponseParticipation,
    val amount: Int,
    val won: Boolean
) {
    fun toBetResultDetail() =
        BetResultDetail(
            betResult = betResult.toBetResult(),
            bet = bet.toBet(),
            participation = participation.toParticipation(),
            amount = amount,
            won = won
        )
}

@Serializable
data class RequestBetFilters(
    val filters: List<BetFilter>
)