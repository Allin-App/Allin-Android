package fr.iut.alldev.allin.data.api.model

import androidx.annotation.Keep
import fr.iut.alldev.allin.data.model.bet.Participation
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ResponseParticipation(
    val id: String,
    val betId: String,
    val userId: String,
    val username: String,
    val answer: String,
    val stake: Int
) {
    fun toParticipation() =
        Participation(
            betId = betId,
            userId = userId,
            username = username,
            response = answer,
            stake = stake
        )
}

@Keep
@Serializable
data class RequestParticipation(
    val betId: String,
    val answer: String,
    val stake: Int
)