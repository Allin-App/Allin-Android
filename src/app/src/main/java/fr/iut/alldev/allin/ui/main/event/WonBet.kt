package fr.iut.alldev.allin.ui.main.event

import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.data.model.bet.BetResultDetail
import fr.iut.alldev.allin.ui.betResult.BetResultBottomSheet

data class WonBet(
    private val user: User,
    val betResult: BetResultDetail,
) : AllInEvent() {
    @Composable
    fun Display(
        sheetState: SheetState,
        onDismiss: () -> Unit
    ) {
        BetResultBottomSheet(
            state = sheetState,
            sheetVisibility = true,
            onDismiss = onDismiss,
            bet = betResult.bet,
            username = user.username,
            coinAmount = betResult.amount,
            stake = betResult.participation.stake,
            winnings = betResult.amount,
            odds = 1f
        )
    }

}
