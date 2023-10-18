package fr.iut.alldev.allin.vo.bet.visitor

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import fr.iut.alldev.allin.test.TestTags

class BetTestVisitor : DisplayBetVisitor {
    @Composable
    override fun visitYesNoBet(b: YesNoBet) {
        Text("This is a YesNo Bet", Modifier.testTag(TestTags.YES_NO_BET.tag))
    }

    @Composable
    override fun visitMatchBet(b: MatchBet) {
        Text("This is a Match Bet", Modifier.testTag(TestTags.MATCH_BET.tag))
    }
}