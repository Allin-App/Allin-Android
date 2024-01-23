package fr.iut.alldev.allin.vo.bet

import androidx.compose.runtime.Composable
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail

interface BetDisplayer {
    @Composable
    fun DisplayBet(betDetail: BetDetail) {
        when (betDetail.bet) {
            is CustomBet -> DisplayCustomBet(betDetail)
            is MatchBet -> DisplayMatchBet(betDetail)
            is YesNoBet -> DisplayYesNoBet(betDetail)
        }
    }

    @Composable
    fun DisplayYesNoBet(betDetail: BetDetail)

    @Composable
    fun DisplayMatchBet(betDetail: BetDetail)

    @Composable
    fun DisplayCustomBet(betDetail: BetDetail)
}