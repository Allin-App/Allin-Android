package fr.iut.alldev.allin.ui.betstatus

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fr.iut.alldev.allin.data.model.BetStatus
import fr.iut.alldev.allin.ui.betstatus.components.BetStatusBottomSheetBack
import fr.iut.alldev.allin.ui.core.AllInBottomSheet


internal const val SHEET_HEIGHT = .85f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BetStatusBottomSheet(
    state: SheetState,
    sheetVisibility: Boolean,
    sheetBackVisibility: Boolean,
    betStatus: BetStatus,
    onDismiss: ()->Unit
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
        BetStatusBottomSheetBack(
            status = betStatus
        )
    }

    AllInBottomSheet(
        sheetVisibility = sheetVisibility,
        onDismiss = onDismiss,
        state = state,
        scrimColor = Color.Transparent
    ){
        Column(
            Modifier
                .fillMaxHeight(SHEET_HEIGHT)
        ) {
        }
    }
}
