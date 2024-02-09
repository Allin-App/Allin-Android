package fr.iut.alldev.allin.ui.main.event

import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable

sealed class AllInEvent {
    @Composable
    abstract fun Display(
        sheetState: SheetState,
        onDismiss: () -> Unit
    )
}