package fr.iut.alldev.allin.ui.ranking.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInCard
import fr.iut.alldev.allin.ui.core.AllInCoinCount
import fr.iut.alldev.allin.ui.core.ProfilePicture

@Composable
fun RankingScreenItem(
    position: Int,
    username: String,
    coins: Int,
    modifier: Modifier = Modifier
) {
    AllInCard(
        modifier = modifier,
        radius = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "$position",
                color = AllInTheme.colors.allInPurple,
                style = AllInTheme.typography.h1,
                fontSize = 15.sp,
                softWrap = false,
                modifier = Modifier.size(width = 26.dp, height = 20.dp)
            )

            ProfilePicture(modifier = Modifier.size(38.dp))

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = username,
                color = AllInTheme.themeColors.onBackground,
                style = AllInTheme.typography.sm2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 15.sp,
                modifier = Modifier.weight(1f)
            )

            AllInCoinCount(
                amount = coins,
                color = AllInTheme.colors.allInPurple
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RankingScreenItemPreview() {
    AllInTheme {
        RankingScreenItem(
            position = 3,
            username = "Username",
            coins = 420
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RankingScreenItemTenthPreview() {
    AllInTheme {
        RankingScreenItem(
            position = 10,
            username = "Username",
            coins = 420
        )
    }
}