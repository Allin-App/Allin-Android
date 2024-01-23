package fr.iut.alldev.allin.data.model.bet.vo

import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.Participation

data class BetDetail(
    val bet: Bet,
    val answers: List<BetAnswerDetail>,
    val participations: List<Participation>,
    val userParticipation: Participation?
) {
    fun getAnswerOfResponse(response: String) =
        answers.find { it.response == response }
}