package fr.iut.alldev.allin.ui.main.event

import androidx.compose.runtime.Composable
import fr.iut.alldev.allin.ui.dailyReward.DailyRewardScreen

data class DailyReward(private val amount: Int) : AllInEvent() {
    @Composable
    fun Display(onDismiss: () -> Unit) {
        DailyRewardScreen(
            amount = amount,
            onDismiss = onDismiss
        )
    }

}
