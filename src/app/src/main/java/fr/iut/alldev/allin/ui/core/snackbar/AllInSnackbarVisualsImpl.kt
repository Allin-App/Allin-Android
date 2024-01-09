package fr.iut.alldev.allin.ui.core.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals

class AllInSnackbarVisualsImpl(
    override val message: String,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean,
    override val duration: SnackbarDuration,
    val type: SnackbarType
) : SnackbarVisuals {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as AllInSnackbarVisualsImpl

        if (message != other.message) return false
        if (actionLabel != other.actionLabel) return false
        if (withDismissAction != other.withDismissAction) return false
        if (duration != other.duration) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = message.hashCode()
        result = 31 * result + actionLabel.hashCode()
        result = 31 * result + withDismissAction.hashCode()
        result = 31 * result + duration.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}
