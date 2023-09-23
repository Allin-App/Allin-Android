package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import fr.iut.alldev.allin.ui.theme.AllInTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllInCard(
    modifier: Modifier = Modifier,
    onClick: (()->Unit)? = null,
    radius: Int = 15,
    backgroundColor: Color = AllInTheme.colors.white,
    borderWidth: Dp? = null,
    borderColor: Color = AllInTheme.colors.allIn_LightGrey100,
    borderBrush: Brush? = null,
    content: @Composable ()->Unit
) {
    onClick?.let {
        Card(
            modifier = modifier.fillMaxWidth(),
            onClick = it,
            shape = AbsoluteRoundedCornerShape(radius),
            border = borderWidth?.let{ width -> borderBrush?.let{BorderStroke(width, it)} ?: BorderStroke(width, borderColor)},
            colors = CardDefaults.cardColors(containerColor = backgroundColor)
        ) {
            content()
        }
    } ?: run {
        Card(
            modifier = modifier.fillMaxWidth(),
            shape = AbsoluteRoundedCornerShape(radius),
            border = borderWidth?.let{ width -> borderBrush?.let{BorderStroke(width, it)} ?: BorderStroke(width, borderColor)},
            colors = CardDefaults.cardColors(containerColor = backgroundColor)
        ) {
            content()
        }
    }

}

