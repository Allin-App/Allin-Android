package fr.iut.alldev.allin.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import fr.iut.alldev.allin.data.model.bet.BinaryBet
import fr.iut.alldev.allin.data.model.bet.CustomBet
import fr.iut.alldev.allin.data.model.bet.MatchBet
import fr.iut.alldev.allin.data.model.bet.NO_VALUE
import fr.iut.alldev.allin.data.model.bet.Participation
import fr.iut.alldev.allin.data.model.bet.YES_VALUE
import fr.iut.alldev.allin.data.model.bet.vo.BetAnswerDetail
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail

class BetDetailPreviewProvider : PreviewParameterProvider<BetDetail> {
    override val values = BetWithStatusPreviewProvider().values.map {

        val answers = when (it) {
            is CustomBet -> listOf(
                BetAnswerDetail(
                    response = "Answer 1",
                    totalStakes = 300,
                    totalParticipants = 8,
                    highestStake = 200,
                    odds = 1.0f
                ),
                BetAnswerDetail(
                    response = "Answer 2",
                    totalStakes = 300,
                    totalParticipants = 4,
                    highestStake = 200,
                    odds = 1.0f
                ),
                BetAnswerDetail(
                    response = "Answer 3",
                    totalStakes = 300,
                    totalParticipants = 2,
                    highestStake = 200,
                    odds = 1.0f
                ),
                BetAnswerDetail(
                    response = "Answer 4",
                    totalStakes = 300,
                    totalParticipants = 1,
                    highestStake = 200,
                    odds = 1.0f
                )
            )

            is MatchBet -> listOf(
                BetAnswerDetail(
                    response = "The Monarchs",
                    totalStakes = 300,
                    totalParticipants = 2,
                    highestStake = 200,
                    odds = 1.0f
                ),
                BetAnswerDetail(
                    response = "Climate Change",
                    totalStakes = 150,
                    totalParticipants = 1,
                    highestStake = 150,
                    odds = 2.0f
                )
            )

            is BinaryBet -> listOf(
                BetAnswerDetail(
                    response = YES_VALUE,
                    totalStakes = 300,
                    totalParticipants = 2,
                    highestStake = 200,
                    odds = 1.0f
                ),
                BetAnswerDetail(
                    response = NO_VALUE,
                    totalStakes = 150,
                    totalParticipants = 1,
                    highestStake = 150,
                    odds = 2.0f
                )
            )
        }

        BetDetail(
            bet = it,
            answers = answers,
            participations = listOf(
                Participation(
                    betId = it.id,
                    id = "1",
                    username = "User1",
                    response = answers.first().response,
                    stake = 100
                ),
                Participation(
                    betId = it.id,
                    id = "2",
                    username = "User 2",
                    response = answers.last().response,
                    stake = 150
                )
            ),
            userParticipation = Participation(
                betId = it.id,
                id = "1",
                username = "User1",
                response = answers.first().response,
                stake = 100
            ),
            wonParticipation = Participation(
                betId = it.id,
                id = "1",
                username = "User1",
                response = answers.first().response,
                stake = 100
            )
        )
    }
}