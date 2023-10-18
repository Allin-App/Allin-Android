package fr.iut.alldev.allin.vo.bet

import androidx.compose.runtime.Composable
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import fr.iut.alldev.allin.vo.ViewObject
import fr.iut.alldev.allin.vo.bet.visitor.DisplayBetVisitor

abstract class BetVO<T: Bet>(val bet: T)
: ViewObject<DisplayBetVisitor> {
    @Composable
    abstract override fun accept(v: DisplayBetVisitor)
}

class YesNoBetVO(bet: YesNoBet) : BetVO<YesNoBet>(bet){
    @Composable
    override fun accept(v: DisplayBetVisitor){
        v.visitYesNoBet(b = bet)
    }
}

class MatchBetVO(bet: MatchBet) : BetVO<MatchBet>(bet){
    @Composable
    override fun accept(v: DisplayBetVisitor){
        v.visitMatchBet(b = bet)
    }
}