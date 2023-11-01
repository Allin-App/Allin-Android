package fr.iut.alldev.allin.ui.betcreation.tabs.sections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInTextField
import fr.iut.alldev.allin.ui.core.AllInTitleInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun QuestionTabThemePhraseSection(
    betTheme: String,
    betThemeError: String?,
    setBetTheme: (String)->Unit,
    betPhrase: String,
    betPhraseError: String?,
    setBetPhrase: (String)->Unit,
    bringIntoViewRequester: BringIntoViewRequester,
    interactionSource: MutableInteractionSource
) {
    AllInTitleInfo(
        text = stringResource(id = R.string.Theme),
        icon = Icons.AutoMirrored.Outlined.HelpOutline,
        modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
        tooltipText = stringResource(id = R.string.Theme_tooltip),
        interactionSource = interactionSource
    )
    AllInTextField(
        placeholder = stringResource(id = R.string.Theme_placeholder),
        value = betTheme,
        onValueChange = setBetTheme,
        bringIntoViewRequester = bringIntoViewRequester,
        borderColor = AllInTheme.colors.white,
        maxChar = 20,
        placeholderFontSize = 13.sp,
        errorText = betThemeError,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(10.dp))
    AllInTitleInfo(
        text = stringResource(id = R.string.Bet_Phrase),
        icon = Icons.AutoMirrored.Outlined.HelpOutline,
        modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
        tooltipText = stringResource(id = R.string.Phrase_tooltip),
        interactionSource = interactionSource
    )
    AllInTextField(
        placeholder = stringResource(id = R.string.Bet_Phrase_placeholder),
        value = betPhrase,
        borderColor = AllInTheme.colors.white,
        onValueChange = setBetPhrase,
        bringIntoViewRequester = bringIntoViewRequester,
        multiLine = true,
        maxChar = 100,
        placeholderFontSize = 13.sp,
        errorText = betPhraseError,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
}