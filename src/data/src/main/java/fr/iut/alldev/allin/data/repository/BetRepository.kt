package fr.iut.alldev.allin.data.repository

import fr.iut.alldev.allin.data.model.bet.Bet

abstract class BetRepository {
    abstract suspend fun createBet(
        bet: Bet,
    )
}