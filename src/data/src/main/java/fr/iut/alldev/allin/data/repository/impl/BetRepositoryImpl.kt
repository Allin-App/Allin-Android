package fr.iut.alldev.allin.data.repository.impl

import fr.iut.alldev.allin.data.api.AllInApi
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.repository.BetRepository
import timber.log.Timber
import javax.inject.Inject

class BetRepositoryImpl @Inject constructor(
    private val api: AllInApi,
) : BetRepository() {
    override suspend fun createBet(bet: Bet) {
        // TODO
        Timber.d("$bet")
    }
}