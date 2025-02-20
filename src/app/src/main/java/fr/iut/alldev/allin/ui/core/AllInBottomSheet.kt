package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.theme.AllInTheme
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllInBottomSheet(
    sheetVisibility: Boolean,
    onDismiss: () -> Unit,
    state: SheetState,
    scrimColor: Color = BottomSheetDefaults.ScrimColor,
    containerColor: Color = AllInTheme.colors.background,
    dragHandle: (@Composable () -> Unit)? = { BottomSheetDefaults.DragHandle() },
    content: @Composable ColumnScope.() -> Unit
) {
    val localDensity = LocalDensity.current
    val localLayoutDirection = LocalLayoutDirection.current

    if (sheetVisibility) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = state,
            containerColor = containerColor,
            scrimColor = scrimColor,
            shape = sheetShape,
            windowInsets = BottomSheetDefaults.windowInsets.let {
                WindowInsets(
                    left = it.getLeft(localDensity, localLayoutDirection),
                    right = it.getRight(localDensity, localLayoutDirection),
                    top = 0,
                    bottom = 0,
                )
            },
            dragHandle = dragHandle,
            content = content
        )
    }
}

private val sheetShape = AbsoluteSmoothCornerShape(
    cornerRadiusTL = 15.dp,
    cornerRadiusTR = 15.dp,
    smoothnessAsPercentTL = 100,
    smoothnessAsPercentTR = 100
)