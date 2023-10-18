package fr.iut.alldev.allin.ui.betstatus.visitor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import fr.iut.alldev.allin.ui.core.StatBar
import fr.iut.alldev.allin.ui.core.bet.BetTitleHeader
import fr.iut.alldev.allin.vo.bet.visitor.DisplayBetVisitor

class BetStatusBottomSheetDisplayBetVisitor : DisplayBetVisitor {
    @Composable
    override fun visitYesNoBet(b: YesNoBet) {
        Column {
            BetTitleHeader(
                title = b.phrase,
                category = b.theme,
                creator = "Lucas" /*TODO : Creator*/,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            StatBar(percentage = .86f)
        }
    }

    @Composable
    override fun visitMatchBet(b: MatchBet) {
        Text("This is a MATCH BET")
    }
}