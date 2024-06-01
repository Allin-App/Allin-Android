package fr.iut.alldev.allin.ui.betStatus.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.ConfigurationCompat
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.BinaryBet
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.NO_VALUE
import fr.iut.alldev.allin.data.model.bet.YES_VALUE
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail
import fr.iut.alldev.allin.ext.formatToSimple
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInCard
import fr.iut.alldev.allin.ui.preview.BetDetailPreviewProvider
import java.util.Locale

private val participationAnswerFontSize = 25.sp

@Composable
fun BetDetail.getParticipationAnswers(): List<@Composable RowScope.() -> Unit> {
    val configuration = LocalConfiguration.current
    val locale = remember { ConfigurationCompat.getLocales(configuration).get(0) ?: Locale.getDefault() }

    return when (this.bet) {
        is CustomBet -> (this.bet as CustomBet).possibleAnswers.map {
            {
                this@getParticipationAnswers.getAnswerOfResponse(it)?.let {
                    ParticipationAnswerLine(
                        text = it.response,
                        odds = it.odds,
                        locale = locale
                    )
                }
            }
        }

        is MatchBet -> buildList {
            val bet = (this@getParticipationAnswers.bet as MatchBet)
            add {
                this@getParticipationAnswers.getAnswerOfResponse(bet.nameTeam1)?.let {
                    ParticipationAnswerLine(
                        text = it.response,
                        odds = it.odds,
                        locale = locale
                    )
                }
            }
            add {
                this@getParticipationAnswers.getAnswerOfResponse(bet.nameTeam2)?.let {
                    ParticipationAnswerLine(
                        text = it.response,
                        color = AllInColorToken.allInBarPink,
                        odds = it.odds,
                        locale = locale
                    )
                }
            }
        }

        is BinaryBet -> buildList {
            add {
                this@getParticipationAnswers.getAnswerOfResponse(YES_VALUE)?.let {
                    ParticipationAnswerLine(
                        text = it.response,
                        odds = it.odds,
                        locale = locale
                    )
                }
            }
            add {
                this@getParticipationAnswers.getAnswerOfResponse(NO_VALUE)?.let {
                    ParticipationAnswerLine(
                        text = it.response,
                        color = AllInColorToken.allInBarPink,
                        odds = it.odds,
                        locale = locale
                    )
                }
            }
        }
    }
}

@Composable
private fun ParticipationAnswerLine(
    text: String,
    color: Color = AllInColorToken.allInBlue,
    locale: Locale,
    odds: Float
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = text.uppercase(),
            color = color,
            style = AllInTheme.typography.h1,
            fontSize = participationAnswerFontSize
        )

        AllInCard(
            radius = 50.dp,
            backgroundColor = AllInColorToken.allInPurple
        ) {
            Box(Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
                Text(
                    text = "x${odds.formatToSimple(locale)}",
                    color = AllInColorToken.white,
                    style = AllInTheme.typography.h2
                )
            }
        }
    }
}

fun Bet.getAnswerFromParticipationIdx(idx: Int) =
    when (this) {
        is CustomBet -> this.possibleAnswers.getOrElse(idx) { "" }
        is MatchBet -> when (idx) {
            0 -> this.nameTeam1
            1 -> this.nameTeam2
            else -> ""
        }

        is BinaryBet -> when (idx) {
            0 -> YES_VALUE
            1 -> NO_VALUE
            else -> ""
        }
    }

@Preview
@Composable
private fun ParticipationAnswersPreview(
    @PreviewParameter(BetDetailPreviewProvider::class) bet: BetDetail,
) {
    AllInTheme {
        Column {
            bet.getParticipationAnswers().forEach {
                Row(Modifier.fillMaxWidth()) { it() }
            }
        }
    }
}