package fr.iut.alldev.allin.data.model.bet

data class BetResult(
    val betId: String,
    val result: String
)

data class BetResultDetail(
    val betResult: BetResult,
    val bet: Bet,
    val participation: Participation,
    val amount: Int,
    val won: Boolean
)