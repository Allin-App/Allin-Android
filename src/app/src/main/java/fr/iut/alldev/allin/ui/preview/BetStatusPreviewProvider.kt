package fr.iut.alldev.allin.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import fr.iut.alldev.allin.data.model.bet.BetStatus

class BetStatusPreviewProvider : PreviewParameterProvider<BetStatus> {
    override val values = BetStatus.entries
}