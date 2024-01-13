package fr.iut.alldev.allin.vo.bet

import androidx.compose.runtime.Composable
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.YesNoBet

interface BetDisplayer {
    @Composable
    fun DisplayBet(bet: Bet){
        when(bet){
            is CustomBet -> DisplayCustomBet(bet)
            is MatchBet -> DisplayMatchBet(bet)
            is YesNoBet -> DisplayYesNoBet(bet)
        }
    }

    @Composable
    fun DisplayYesNoBet(b: YesNoBet)

    @Composable
    fun DisplayMatchBet(b: MatchBet)

    @Composable
    fun DisplayCustomBet(b: CustomBet)
}