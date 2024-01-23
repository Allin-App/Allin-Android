package fr.iut.alldev.allin.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.YesNoBet
import java.time.ZonedDateTime


class BetWithStatusPreviewProvider : PreviewParameterProvider<Bet> {
    override val values = BetStatus.entries.flatMap { status ->
        sequenceOf(
            YesNoBet(
                id = "1",
                theme = "Theme",
                phrase = "Phrase",
                endRegisterDate = ZonedDateTime.now(),
                endBetDate = ZonedDateTime.now(),
                isPublic = true,
                betStatus = status,
                creator = "creator"
            ),
            MatchBet(
                id = "2",
                theme = "Theme",
                phrase = "Phrase",
                endRegisterDate = ZonedDateTime.now(),
                endBetDate = ZonedDateTime.now(),
                isPublic = true,
                betStatus = status,
                creator = "creator",
                nameTeam1 = "The Monarchs",
                nameTeam2 = "Climate Change"
            ),
            CustomBet(
                id = "3",
                theme = "Theme",
                phrase = "Phrase",
                endRegisterDate = ZonedDateTime.now(),
                endBetDate = ZonedDateTime.now(),
                isPublic = true,
                betStatus = status,
                creator = "creator",
                possibleAnswers = listOf(
                    "Answer 1",
                    "Answer 2",
                    "Answer 3",
                    "Answer 4"
                )

            )
        )
    }
}
