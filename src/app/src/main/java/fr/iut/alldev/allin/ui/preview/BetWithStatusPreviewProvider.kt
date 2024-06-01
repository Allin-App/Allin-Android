package fr.iut.alldev.allin.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import fr.iut.alldev.allin.data.model.bet.Bet
import fr.iut.alldev.allin.data.model.bet.BetStatus
import fr.iut.alldev.allin.data.model.bet.BinaryBet
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import java.time.ZonedDateTime


class BetWithStatusPreviewProvider : PreviewParameterProvider<Bet> {
    override val values = BetStatus.entries.asSequence().flatMap { status ->
        sequenceOf(
            BinaryBet(
                id = "1",
                theme = "Theme",
                phrase = "Phrase",
                endRegisterDate = ZonedDateTime.now(),
                endBetDate = ZonedDateTime.now(),
                isPublic = true,
                betStatus = status,
                creator = "creator",
                totalStakes = 0,
                totalParticipants = 0
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
                nameTeam2 = "Climate Change",
                totalStakes = 0,
                totalParticipants = 0
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
                ),
                totalStakes = 0,
                totalParticipants = 0
            )
        )
    }
}
