package fr.iut.alldev.allin.ui.betcreation.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.iut.alldev.allin.ui.core.AllInCoinCount
import fr.iut.alldev.allin.ui.core.AllInRadioButton
import fr.iut.alldev.allin.ui.core.ProfilePicture
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun BetCreationScreenFriendLine(
    username: String,
    allCoinsAmount: Int,
    isSelected: Boolean,
    onClick: ()->Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(
                if (isSelected) AllInTheme.colors.allIn_Purple.copy(alpha = .13f)
                else AllInTheme.themeColors.background
            )
            .padding(15.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AllInRadioButton(
            checked = isSelected,
            modifier = Modifier.padding(end = 7.dp)
        )
        ProfilePicture(modifier = Modifier.size(25.dp))
        Text(
            text = username,
            fontWeight = FontWeight.Bold,
            style = AllInTheme.typography.h2,
            color = AllInTheme.themeColors.on_main_surface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        AllInCoinCount(
            amount = allCoinsAmount,
            color = AllInTheme.colors.allIn_Purple
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetCreationScreenFriendLinePreview() {
    AllInTheme {
        BetCreationScreenFriendLine(
            username = "David",
            allCoinsAmount = 542,
            isSelected = false) {

        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetCreationScreenFriendLineSelectedPreview() {
    AllInTheme {
        BetCreationScreenFriendLine(
            username = "David",
            allCoinsAmount = 542,
            isSelected = true) {

        }
    }
}