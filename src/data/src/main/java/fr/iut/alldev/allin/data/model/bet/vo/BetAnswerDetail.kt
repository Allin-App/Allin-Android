package fr.iut.alldev.allin.data.model.bet.vo

data class BetAnswerDetail(
    val response: String,
    val totalStakes: Int,
    val totalParticipants: Int,
    val highestStake: Int,
    val odds: Float
)