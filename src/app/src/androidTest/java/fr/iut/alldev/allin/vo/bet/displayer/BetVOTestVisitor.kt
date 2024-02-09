package fr.iut.alldev.allin.vo.bet.displayer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail
import fr.iut.alldev.allin.test.TestTags
import fr.iut.alldev.allin.vo.bet.BetDisplayer

class BetTestDisplayer : BetDisplayer {
    @Composable
    override fun DisplayYesNoBet(betDetail: BetDetail) {
        Text("This is a YesNo Bet", Modifier.testTag(TestTags.YES_NO_BET.tag))
    }

    @Composable
    override fun DisplayMatchBet(betDetail: BetDetail) {
        Text("This is a Match Bet", Modifier.testTag(TestTags.MATCH_BET.tag))
    }

    @Composable
    override fun DisplayCustomBet(betDetail: BetDetail) {
        Text("This is a Custom Bet", Modifier.testTag(TestTags.CUSTOM_BET.tag))
    }
}