package fr.iut.alldev.allin.data.repository

import fr.iut.alldev.allin.data.api.model.RequestBet
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.BetFilter
import fr.iut.alldev.allin.data.model.bet.BetResultDetail
import fr.iut.alldev.allin.data.model.bet.Participation
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail

abstract class BetRepository {
    abstract suspend fun createBet(bet: RequestBet, token: String)
    abstract suspend fun getHistory(token: String): List<BetResultDetail>
    abstract suspend fun getCurrentBets(token: String): List<BetDetail>
    abstract suspend fun getBet(id: String, token: String): BetDetail
    abstract suspend fun participateToBet(participation: Participation, token: String)
    abstract suspend fun getAllBets(token: String, filters: List<BetFilter>): List<Bet>
    abstract suspend fun getPopularBet(token: String): Bet?
    abstract suspend fun confirmBet(token: String, id: String, response: String)
    abstract suspend fun getToConfirm(token: String): List<BetDetail>
    abstract suspend fun getWon(token: String): List<BetResultDetail>
}