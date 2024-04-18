package fr.iut.alldev.allin.ui.ranking.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInCard
import fr.iut.alldev.allin.ui.core.AllInCoinCount
import fr.iut.alldev.allin.ui.core.ProfilePicture
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@Composable
fun RankingScreenFirst(
    username: String,
    coins: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        AllInCard(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(top = 35.dp),
            shape = AbsoluteSmoothCornerShape(
                cornerRadiusTL = 41.dp,
                smoothnessAsPercentTL = 100,
                cornerRadiusTR = 8.dp,
                smoothnessAsPercentTR = 100,
                cornerRadiusBR = 19.dp,
                smoothnessAsPercentBR = 100,
                cornerRadiusBL = 19.dp,
                smoothnessAsPercentBL = 100
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = username,
                    color = AllInTheme.themeColors.onBackground,
                    style = AllInTheme.typography.h1,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )


                HorizontalDivider(color = AllInTheme.themeColors.border)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AllInTheme.themeColors.background2)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AllInCoinCount(
                        amount = coins,
                        color = AllInTheme.colors.allInPurple,
                        size = 20
                    )
                }
            }
        }
        ProfilePicture(
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.TopCenter)
                .zIndex(1f),
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(vertical = 55.dp)
                .zIndex(500f)
                .clip(CircleShape)
                .background(AllInTheme.colors.allInPurple)
                .size(30.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "1",
                color = AllInTheme.colors.white,
                style = AllInTheme.typography.h1,
                fontSize = 20.sp,
                modifier = Modifier.padding(4.dp)
            )
        }

    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun RankingScreenFirstPreview() {
    AllInTheme {
        RankingScreenFirst(
            username = "Username",
            coins = 420
        )
    }
}