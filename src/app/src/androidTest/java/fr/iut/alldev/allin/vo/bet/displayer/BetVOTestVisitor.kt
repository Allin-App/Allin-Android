package fr.iut.alldev.allin.vo.bet.displayer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import fr.iut.alldev.allin.test.TestTags
import fr.iut.alldev.allin.vo.bet.BetDisplayer

class BetTestDisplayer : BetDisplayer {
    @Composable
    override fun DisplayYesNoBet(b: YesNoBet) {
        Text("This is a YesNo Bet", Modifier.testTag(TestTags.YES_NO_BET.tag))
    }

    @Composable
    override fun DisplayMatchBet(b: MatchBet) {
        Text("This is a Match Bet", Modifier.testTag(TestTags.MATCH_BET.tag))
    }

    @Composable
    override fun DisplayCustomBet(b: CustomBet) {
        Text("This is a Custom Bet", Modifier.testTag(TestTags.CUSTOM_BET.tag))
    }
}