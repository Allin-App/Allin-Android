package fr.iut.alldev.allin.data.repository

import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.Participation
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail
import kotlinx.coroutines.flow.Flow

abstract class BetRepository {
    abstract suspend fun createBet(bet: Bet, token: String)
    abstract suspend fun getHistory(): Flow<List<Bet>>
    abstract suspend fun getCurrentBets(): Flow<List<Bet>>
    abstract suspend fun getBet(id: String, token: String): BetDetail
    abstract suspend fun participateToBet(participation: Participation, token: String)
    abstract suspend fun getAllBets(): List<Bet>
}