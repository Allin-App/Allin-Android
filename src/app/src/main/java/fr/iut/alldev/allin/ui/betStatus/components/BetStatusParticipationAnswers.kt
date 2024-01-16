package fr.iut.alldev.allin.ui.betStatus.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.NO_VALUE
import fr.iut.alldev.allin.data.model.bet.YES_VALUE
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.preview.BetPreviewProvider

private val participationAnswerFontSize = 25.sp

@Composable
fun Bet.getParticipationAnswers(): List<@Composable RowScope.() -> Unit> =
    when (this) {
        is CustomBet -> this.possibleAnswers.map {
            {
                Text(
                    text = it,
                    color = AllInTheme.colors.allInBlue,
                    style = AllInTheme.typography.h1,
                    fontSize = participationAnswerFontSize
                )
            }
        }

        is MatchBet -> listOf(
            {
                Text(
                    text = this@getParticipationAnswers.nameTeam1,
                    color = AllInTheme.colors.allInBlue,
                    style = AllInTheme.typography.h1,
                    fontSize = participationAnswerFontSize
                )
            },
            {
                Text(
                    text = this@getParticipationAnswers.nameTeam2,
                    color = AllInTheme.colors.allInBarPink,
                    style = AllInTheme.typography.h1,
                    fontSize = participationAnswerFontSize
                )
            }
        )

        is YesNoBet -> listOf(
            {
                Text(
                    text = stringResource(id = R.string.Yes).uppercase(),
                    color = AllInTheme.colors.allInBlue,
                    style = AllInTheme.typography.h1,
                    fontSize = participationAnswerFontSize
                )
            },
            {
                Text(
                    text = stringResource(id = R.string.No).uppercase(),
                    color = AllInTheme.colors.allInBarPink,
                    style = AllInTheme.typography.h1,
                    fontSize = participationAnswerFontSize
                )
            }
        )
    }

fun Bet.getAnswerFromParticipationIdx(idx: Int) =
    when (this) {
        is CustomBet -> this.possibleAnswers.getOrElse(idx) { "" }
        is MatchBet -> when (idx) {
            0 -> this.nameTeam1
            1 -> this.nameTeam2
            else -> ""
        }

        is YesNoBet -> when (idx) {
            0 -> YES_VALUE
            1 -> NO_VALUE
            else -> ""
        }
    }

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ParticipationAnswersPreview(
    @PreviewParameter(BetPreviewProvider::class) bet: Bet,
) {
    AllInTheme {
        Column {
            bet.getParticipationAnswers().forEach {
                Row(Modifier.fillMaxWidth()) { it() }
            }
        }
    }
}