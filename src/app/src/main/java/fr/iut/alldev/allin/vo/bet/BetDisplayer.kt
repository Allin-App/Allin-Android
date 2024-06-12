package fr.iut.alldev.allin.vo.bet

import androidx.compose.runtime.Composable
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.data.model.bet.BinaryBet
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail

interface BetDisplayer {
    @Composable
    fun DisplayBet(betDetail: BetDetail, currentUser: User) {
        when (betDetail.bet) {
            is CustomBet -> DisplayCustomBet(betDetail, currentUser)
            is MatchBet -> DisplayMatchBet(betDetail, currentUser)
            is BinaryBet -> DisplayBinaryBet(betDetail, currentUser)
        }
    }

    @Composable
    fun DisplayBinaryBet(betDetail: BetDetail, currentUser: User)

    @Composable
    fun DisplayMatchBet(betDetail: BetDetail, currentUser: User)

    @Composable
    fun DisplayCustomBet(betDetail: BetDetail, currentUser: User)
}