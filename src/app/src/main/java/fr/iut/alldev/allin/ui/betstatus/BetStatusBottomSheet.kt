package fr.iut.alldev.allin.ui.betstatus

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.ui.betstatus.components.BetStatusBottomSheetBack
import fr.iut.alldev.allin.ui.betstatus.visitor.BetStatusBottomSheetDisplayBetVisitor
import fr.iut.alldev.allin.ui.core.AllInBottomSheet
import fr.iut.alldev.allin.vo.bet.BetVO


internal const val SHEET_HEIGHT = .85f


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BetStatusBottomSheet(
    state: SheetState,
    sheetVisibility: Boolean,
    sheetBackVisibility: Boolean,
    bet: BetVO<Bet>?,
    onDismiss: ()->Unit,
    visitor: BetStatusBottomSheetDisplayBetVisitor
) {
    AnimatedVisibility(
        visible = sheetBackVisibility,
        enter = slideInVertically(
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            targetOffsetY = { it }
        )
    ) {
        bet?.let {
            BetStatusBottomSheetBack(
                status = it.bet.betStatus
            )
        }
    }

    AllInBottomSheet(
        sheetVisibility = sheetVisibility,
        onDismiss = onDismiss,
        state = state,
        scrimColor = Color.Transparent
    ){
        Column(
            Modifier.fillMaxHeight(SHEET_HEIGHT)
        ) {
            bet?.Accept(visitor)
        }
    }
}
