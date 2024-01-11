package fr.iut.alldev.allin.data.repository

import fr.iut.alldev.allin.data.model.bet.Bet
import kotlinx.coroutines.flow.Flow

abstract class BetRepository {
    abstract suspend fun createBet(bet: Bet, token: String)
    abstract suspend fun getHistory(): Flow<List<Bet>>
    abstract suspend fun getCurrentBets(): Flow<List<Bet>>
    abstract suspend fun getAllBets(): Flow<List<Bet>>
}