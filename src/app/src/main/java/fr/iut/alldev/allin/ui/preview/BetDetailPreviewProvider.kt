package fr.iut.alldev.allin.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import fr.iut.alldev.allin.data.model.bet.NO_VALUE
import fr.iut.alldev.allin.data.model.bet.Participation
import fr.iut.alldev.allin.data.model.bet.YES_VALUE
import fr.iut.alldev.allin.data.model.bet.vo.BetAnswerDetail
import fr.iut.alldev.allin.data.model.bet.vo.BetDetail

class BetDetailPreviewProvider : PreviewParameterProvider<BetDetail> {
    override val values = BetWithStatusPreviewProvider().values.map {
        BetDetail(
            bet = it,
            answers = listOf(
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
            ),
            participations = listOf(
                Participation(
                    betId = it.id,
                    username = "User1",
                    response = YES_VALUE,
                    stake = 200
                ),
                Participation(
                    betId = it.id,
                    username = "User2",
                    response = YES_VALUE,
                    stake = 100
                ),
                Participation(
                    betId = it.id,
                    username = "MyUser",
                    response = NO_VALUE,
                    stake = 150
                )
            ),
            userParticipation = null /*Participation(
                betId = it.id,
                username = "MyUser",
                response = NO_VALUE,
                stake = 150
            )*/
        )
    }
}