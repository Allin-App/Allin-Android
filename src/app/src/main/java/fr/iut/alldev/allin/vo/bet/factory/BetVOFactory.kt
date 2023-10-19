package fr.iut.alldev.allin.vo.bet.factory

import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import fr.iut.alldev.allin.vo.bet.BetVO
import fr.iut.alldev.allin.vo.bet.MatchBetVO
import fr.iut.alldev.allin.vo.bet.YesNoBetVO

private val betTypeToVOMap = mapOf(
    YesNoBet::class.java to YesNoBetVOFactory(),
    MatchBet::class.java to MatchBetVOFactory()
)
abstract class BetVOFactory<out T : Bet> {
    abstract fun create(bet: @UnsafeVariance T): BetVO<@UnsafeVariance T>
}

class YesNoBetVOFactory : BetVOFactory<YesNoBet>() {
    override fun create(bet: YesNoBet) =
        YesNoBetVO(bet)
}

class MatchBetVOFactory : BetVOFactory<MatchBet>() {
    override fun create(bet: MatchBet) =
        MatchBetVO(bet)
}

fun Bet.toBetVO() = betTypeToVOMap[this.javaClass]?.create(this)


