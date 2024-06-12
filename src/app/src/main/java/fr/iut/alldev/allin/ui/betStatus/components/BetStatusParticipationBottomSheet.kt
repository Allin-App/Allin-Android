package fr.iut.alldev.allin.ui.betStatus.components

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ext.bottomSheetNavigationBarsPadding
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInBottomSheet
import fr.iut.alldev.allin.ui.core.AllInButton
import fr.iut.alldev.allin.ui.core.AllInCoinCount
import fr.iut.alldev.allin.ui.core.AllInIntTextField
import fr.iut.alldev.allin.ui.core.AllInSelectionBox
import fr.iut.alldev.allin.ui.core.topbar.AllInTopBarCoinCounter
import kotlinx.coroutines.launch
import kotlin.math.ln
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BetStatusParticipationBottomSheet(
    sheetVisibility: Boolean,
    betPhrase: String,
    coinAmount: Int,
    onDismiss: () -> Unit,
    state: SheetState,
    enabled: Boolean,
    odds: Float,
    stake: Int?,
    setStake: (Int?) -> Unit,
    elements: List<@Composable RowScope.() -> Unit>,
    selectedElement: (@Composable RowScope.() -> Unit)?,
    setElement: (Int) -> Unit,
    onParticipate: () -> Unit,

    ) {
    val scope = rememberCoroutineScope()
    AllInBottomSheet(
        sheetVisibility = sheetVisibility,
        onDismiss = onDismiss,
        state = state,
        containerColor = AllInTheme.colors.background2
    ) {
        BetStatusParticipationBottomSheetContent(
            betPhrase = betPhrase,
            coinAmount = coinAmount,
            elements = elements,
            selectedElement = selectedElement,
            setElement = setElement,
            enabled = enabled,
            stake = stake,
            odds = odds,
            setStake = setStake
        ) {
            scope.launch {
                onParticipate()
                state.hide()
                onDismiss()
            }
        }
    }
}

@Composable
private fun BetStatusParticipationBottomSheetContent(
    betPhrase: String,
    coinAmount: Int,
    enabled: Boolean,
    stake: Int?,
    odds: Float,
    setStake: (Int?) -> Unit,
    selectedElement: (@Composable RowScope.() -> Unit)?,
    elements: List<@Composable RowScope.() -> Unit>,
    setElement: (Int) -> Unit,
    onButtonClick: () -> Unit
) {
    val (answersBoxIsOpen, setAnswersBoxIsOpen) = remember { mutableStateOf(false) }
    val betStrength by animateFloatAsState(
        targetValue = if (enabled) {
            (ln(stake?.toFloat() ?: 0f) / ln(coinAmount.toFloat())).coerceIn(.42f..1f)
        } else .42f,
        label = ""
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.bet_status_place_your_bets),
            style = AllInTheme.typography.h2,
            color = AllInTheme.colors.onMainSurface,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 18.dp)
        )
        AllInTopBarCoinCounter(
            amount = coinAmount,
            backgroundColor = AllInColorToken.allInBlue,
            textColor = AllInColorToken.white,
            iconColor = AllInColorToken.white,
        )
    }

    Column(
        modifier = Modifier.padding(horizontal = 18.dp)
    ) {
        Text(
            text = betPhrase,
            style = AllInTheme.typography.p2,
            color = AllInTheme.colors.onMainSurface,
            modifier = Modifier.padding(vertical = 30.dp)
        )
        AllInSelectionBox(
            isOpen = answersBoxIsOpen,
            setIsOpen = setAnswersBoxIsOpen,
            selected = selectedElement,
            setSelected = { setElement(elements.indexOf(it)) },
            elements = elements,
            borderWidth = 1.dp
        )
        Spacer(modifier = Modifier.height(8.dp))
        AllInIntTextField(
            value = stake,
            setValue = { setStake(it?.coerceAtMost(coinAmount)) },
            textStyle = AllInTheme.typography.h1.copy(
                fontSize = 20.sp,
                color = AllInColorToken.allInPurple
            ),
            placeholder = stringResource(id = R.string.bet_result_stake),
            trailingIcon = AllInTheme.icons.allCoins(),
            trailingIconColor = if (enabled) AllInColorToken.allInPurple else null,
            borderColor = if (enabled) AllInColorToken.allInPurple.copy(alpha = betStrength) else AllInTheme.colors.border,
            modifier = Modifier.fillMaxWidth(),
            maxChar = null
        )
    }
    Spacer(modifier = Modifier.height(100.dp))
    HorizontalDivider(color = AllInTheme.colors.border)
    Column(
        modifier = Modifier
            .background(AllInTheme.colors.background)
            .padding(7.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.participation_possible_winnings),
                style = AllInTheme.typography.p1,
                color = AllInTheme.colors.onBackground
            )
            AllInCoinCount(
                amount = stake?.let { (it * odds).roundToInt() } ?: 0,
                color = AllInTheme.colors.onBackground
            )
        }
        AllInButton(
            enabled = enabled,
            color = AllInColorToken.allInPurple,
            text = stringResource(id = R.string.bet_participate),
            textColor = AllInColorToken.white,
            radius = 5.dp,
            onClick = onButtonClick,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(bottomSheetNavigationBarsPadding()))
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetStatusParticipationBottomSheetContentPreview() {
    AllInTheme {
        Column {
            BetStatusParticipationBottomSheetContent(
                betPhrase = "Bet phrase",
                coinAmount = 3620,
                onButtonClick = {},
                elements = emptyList(),
                setElement = {},
                selectedElement = null,
                enabled = true,
                stake = 123,
                odds = 0.42f,
                setStake = {}
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BetStatusParticipationBottomSheetContentEmptyPreview() {
    AllInTheme {
        Column {
            BetStatusParticipationBottomSheetContent(
                betPhrase = "Bet phrase",
                coinAmount = 3620,
                onButtonClick = {},
                elements = emptyList(),
                setElement = {},
                selectedElement = null,
                enabled = true,
                stake = null,
                odds = 0.42f,
                setStake = {}
            )
        }
    }
}