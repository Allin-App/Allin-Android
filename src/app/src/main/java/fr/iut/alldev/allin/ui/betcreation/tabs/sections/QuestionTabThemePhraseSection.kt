package fr.iut.alldev.allin.ui.betcreation.tabs.sections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.core.AllInTextAndIcon
import fr.iut.alldev.allin.ui.core.AllInTextField
import fr.iut.alldev.allin.ui.theme.AllInTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun QuestionTabThemePhraseSection(
    betTheme: String,
    setBetTheme: (String)->Unit,
    betPhrase: String,
    setBetPhrase: (String)->Unit,
    bringIntoViewRequester: BringIntoViewRequester
) {
    AllInTextAndIcon(
        text = stringResource(id = R.string.Theme),
        icon = Icons.AutoMirrored.Outlined.HelpOutline,
        modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
        onClick = {}
    )
    AllInTextField(
        placeholder = stringResource(id = R.string.Theme_placeholder),
        value = betPhrase,
        onValueChange = setBetPhrase,
        bringIntoViewRequester = bringIntoViewRequester,
        borderColor = AllInTheme.colors.white,
        maxChar = 20,
        placeholderFontSize = 13.sp,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(12.dp))
    AllInTextAndIcon(
        text = stringResource(id = R.string.Bet_Phrase),
        icon = Icons.AutoMirrored.Outlined.HelpOutline,
        modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
        onClick = {}
    )
    AllInTextField(
        placeholder = stringResource(id = R.string.Bet_Phrase_placeholder),
        value = betTheme,
        borderColor = AllInTheme.colors.white,
        onValueChange = setBetTheme,
        bringIntoViewRequester = bringIntoViewRequester,
        multiLine = true,
        maxChar = 100,
        placeholderFontSize = 13.sp,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
}