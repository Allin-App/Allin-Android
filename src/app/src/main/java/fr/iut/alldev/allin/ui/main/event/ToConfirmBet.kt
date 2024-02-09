package fr.iut.alldev.allin.ui.main.event

import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail
import fr.iut.alldev.allin.ui.betConfirmation.BetConfirmationBottomSheet

data class ToConfirmBet(
    private val betDetail: BetDetail,
    private val onConfirm: (String) -> Unit
) : AllInEvent() {
    @Composable
    override fun Display(
        sheetState: SheetState,
        onDismiss: () -> Unit
    ) {
        BetConfirmationBottomSheet(
            state = sheetState,
            sheetVisibility = true,
            betDetail = betDetail,
            onDismiss = onDismiss,
            onConfirm = onConfirm
        )
    }

    override fun hashCode(): Int {
        return betDetail.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ToConfirmBet

        return betDetail == other.betDetail
    }

}
