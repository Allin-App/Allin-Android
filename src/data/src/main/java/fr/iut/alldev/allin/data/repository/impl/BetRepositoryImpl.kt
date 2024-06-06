package fr.iut.alldev.allin.data.repository.impl

import fr.iut.alldev.allin.data.api.AllInApi
import fr.iut.alldev.allin.data.api.AllInApi.Companion.asRequestBody
import fr.iut.alldev.allin.data.api.AllInApi.Companion.formatBearerToken
import fr.iut.alldev.allin.data.api.model.RequestBet
import fr.iut.alldev.allin.data.api.model.RequestBetFilters
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.BetFilter
import fr.iut.alldev.allin.data.model.bet.BetResultDetail
import fr.iut.alldev.allin.data.model.bet.Participation
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail
import fr.iut.alldev.allin.data.repository.BetRepository
import javax.inject.Inject

class BetRepositoryImpl @Inject constructor(
    private val api: AllInApi
) : BetRepository() {
    override suspend fun createBet(bet: RequestBet, token: String) {
        api.createBet(
            token = token.formatBearerToken(),
            body = bet
        )
    }

    override suspend fun getHistory(token: String): List<BetResultDetail> {
        return api.getBetHistory(token.formatBearerToken()).map {
            it.toBetResultDetail()
        }
    }

    override suspend fun getCurrentBets(token: String): List<BetDetail> {
        return api.getBetCurrent(token.formatBearerToken()).map {
            it.toBetDetail()
        }
    }

    override suspend fun getBet(id: String, token: String): BetDetail {
        return api.getBet(
            token = token.formatBearerToken(),
            id = id
        ).toBetDetail()
    }

    override suspend fun participateToBet(participation: Participation, token: String) {
        api.participateToBet(
            token = token.formatBearerToken(),
            body = participation.toRequestParticipation()
        )
    }

    override suspend fun getAllBets(token: String, filters: List<BetFilter>): List<Bet> =
        api.getAllBets(
            token.formatBearerToken(),
            RequestBetFilters(filters)
        ).map { it.toBet() }

    override suspend fun getPopularBet(token: String): Bet? =
        api.getPopularBet(token.formatBearerToken())?.toBet()

    override suspend fun getToConfirm(token: String): List<BetDetail> =
        api.getToConfirm(token.formatBearerToken()).map { it.toBetDetail() }

    override suspend fun getWon(token: String): List<BetResultDetail> =
        api.getWon(token.formatBearerToken()).map { it.toBetResultDetail() }

    override suspend fun confirmBet(token: String, id: String, response: String) {
        api.confirmBet(token.formatBearerToken(), id, response.asRequestBody())
    }

}