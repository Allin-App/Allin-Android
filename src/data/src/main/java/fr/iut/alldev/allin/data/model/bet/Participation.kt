package fr.iut.alldev.allin.data.model.bet

import fr.iut.alldev.allin.data.api.model.RequestParticipation

data class Participation(
    val betId: String,
    val id: String,
    val username: String,
    val response: String,
    val stake: Int
) {
    fun toRequestParticipation() =
        RequestParticipation(
            betId = betId,
            answer = response,
            stake = stake
        )
}