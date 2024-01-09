package fr.iut.alldev.allin.data.repository

import fr.iut.alldev.allin.data.model.bet.Bet
import kotlinx.coroutines.flow.Flow

abstract class BetRepository {
    abstract suspend fun createBet(bet: Bet)
    abstract suspend fun getHistory(): Flow<List<Bet>>
    abstract suspend fun getCurrentBets(): Flow<List<Bet>>
}