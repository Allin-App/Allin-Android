package fr.iut.alldev.allin.ui.core.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.theme.AllInTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllInSnackbar(
    snackbarState: SnackbarHostState
) {
    SnackbarHost(
        hostState = snackbarState
    ) { snackbarData ->
        val dismissState = rememberSwipeToDismissState(
            confirmValueChange = { value ->
                if (value != SwipeToDismissValue.Settled) {
                    snackbarState.currentSnackbarData?.dismiss()
                    true
                } else {
                    false
                }
            }
        )
        SwipeToDismissBox(
            state = dismissState,
            backgroundContent = {},
            modifier = Modifier.padding(8.dp)
        ) {
            val snackbarType = remember {
                if (snackbarData.visuals is AllInSnackbarVisualsImpl) {
                    (snackbarData.visuals as AllInSnackbarVisualsImpl).type
                } else {
                    SnackbarType.STANDARD
                }
            }

            AllInSnackbarContent(
                backgroundColor = snackbarType.getBackgroundColor(),
                contentColor = AllInTheme.colors.white,
                text = snackbarData.visuals.message,
                icon = snackbarType.getIcon(),
                dismiss = { snackbarState.currentSnackbarData?.dismiss() }
            )

        }
    }
}

@Composable
fun AllInSnackbarContent(
    backgroundColor: Color,
    contentColor: Color,
    text: String,
    icon: ImageVector,
    dismiss: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 4.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = text,
                color = contentColor,
                style = AllInTheme.typography.r,
                overflow = TextOverflow.Ellipsis,
                maxLines = 5,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = dismiss,
                modifier = Modifier
                    .size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

private class SnackbarTypePreviewProvider : PreviewParameterProvider<SnackbarType> {
    override val values = SnackbarType.entries.asSequence()
}

@Preview
@Composable
private fun AllInSnackbarContentPreview(
    @PreviewParameter(SnackbarTypePreviewProvider::class) snackbarType: SnackbarType
) {
    AllInTheme {
        AllInSnackbarContent(
            backgroundColor = snackbarType.getBackgroundColor(),
            contentColor = AllInTheme.colors.white,
            text = "Lorem Ipsum",
            icon = snackbarType.getIcon(),
            dismiss = {}
        )
    }
}

