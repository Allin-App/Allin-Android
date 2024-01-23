package fr.iut.alldev.allin.data.model.bet.vo

import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.Participation

data class BetResult(
    val bet: Bet,
    val participations: List<Participation>,
    val answerDetail: BetAnswerDetail
)