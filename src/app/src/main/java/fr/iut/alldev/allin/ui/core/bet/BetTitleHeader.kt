package fr.iut.alldev.allin.ui.core.bet

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.HighlightedText

@Composable
fun BetTitleHeader(
    title: String,
    category: String,
    creator: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Row(
            Modifier.align(Alignment.End)
        ) {
            HighlightedText(
                text = stringResource(id = R.string.Proposed_by_x, creator),
                query = creator,
                highlightStyle = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = AllInTheme.themeColors.onMainSurface
                ),
                fontSize = 12.sp,
                style = AllInTheme.typography.p2,
                color = AllInTheme.themeColors.onBackground2
            )
        }
        Spacer(modifier = Modifier.height(11.dp))
        Text(
            text = category,
            fontSize = 15.sp,
            color = AllInTheme.themeColors.onBackground2,
            style = AllInTheme.typography.sm2
        )
        Text(
            text = title,
            fontSize = 20.sp,
            color = AllInTheme.themeColors.onMainSurface,
            style = AllInTheme.typography.h1
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetTitleHeaderPreview() {
    AllInTheme {
        BetTitleHeader(
            title = "Emre va réussir son TP de CI/CD mercredi?",
            category = "Études",
            creator = "Lucas"
        )
    }
}