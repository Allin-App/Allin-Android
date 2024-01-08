package fr.iut.alldev.allin.data.model.bet

sealed class BetStatus {
    data class Finished(val status: BetFinishedStatus) : BetStatus()

    data object InProgress : BetStatus()

    data object Waiting : BetStatus()
}