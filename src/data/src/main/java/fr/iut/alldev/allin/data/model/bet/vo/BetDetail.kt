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

    fun getPercentageOfAnswer(answerDetail: BetAnswerDetail): Float =
        (answerDetail.totalParticipants.toFloat() / answers.sumOf { it.totalParticipants }).let {
            if (it.isNaN() || it.isInfinite()) {
                1f / this.answers.size
            } else {
                it
            }
        }
}