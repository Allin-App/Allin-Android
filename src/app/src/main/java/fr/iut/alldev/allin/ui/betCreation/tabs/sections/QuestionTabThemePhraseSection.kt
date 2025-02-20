package fr.iut.alldev.allin.ui.betCreation.tabs.sections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.ui.core.AllInTextField
import fr.iut.alldev.allin.ui.core.AllInTitleInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun QuestionTabThemePhraseSection(
    betTheme: String,
    betThemeError: String?,
    setBetTheme: (String) -> Unit,
    betPhrase: String,
    betPhraseError: String?,
    setBetPhrase: (String) -> Unit,
    interactionSource: MutableInteractionSource
) {
    AllInTitleInfo(
        text = stringResource(id = R.string.bet_creation_theme),
        icon = Icons.AutoMirrored.Outlined.HelpOutline,
        modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
        tooltipText = stringResource(id = R.string.bet_creation_theme_tooltip),
        interactionSource = interactionSource
    )
    AllInTextField(
        placeholder = stringResource(id = R.string.bet_creation_theme_placeholder),
        value = betTheme,
        modifier = Modifier.fillMaxWidth(),
        maxChar = 20,
        placeholderFontSize = 13.sp,
        onValueChange = setBetTheme,
        errorText = betThemeError,
        borderColor = AllInColorToken.white
    )
    Spacer(modifier = Modifier.height(10.dp))
    AllInTitleInfo(
        text = stringResource(id = R.string.bet_creation_bet_phrase),
        icon = Icons.AutoMirrored.Outlined.HelpOutline,
        modifier = Modifier.padding(start = 11.dp, bottom = 8.dp),
        tooltipText = stringResource(id = R.string.bet_creation_phrase_tooltip),
        interactionSource = interactionSource
    )
    AllInTextField(
        placeholder = stringResource(id = R.string.bet_creation_bet_phrase_placeholder),
        value = betPhrase,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        maxChar = 100,
        placeholderFontSize = 13.sp,
        multiLine = true,
        onValueChange = setBetPhrase,
        errorText = betPhraseError,
        borderColor = AllInColorToken.white
    )
}