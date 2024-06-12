package fr.iut.alldev.allin.ext

import androidx.annotation.StringRes
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.bet.BetFilter
import fr.iut.alldev.allin.data.model.bet.BetFilter.FINISHED
import fr.iut.alldev.allin.data.model.bet.BetFilter.INVITATION
import fr.iut.alldev.allin.data.model.bet.BetFilter.IN_PROGRESS
import fr.iut.alldev.allin.data.model.bet.BetFilter.PUBLIC

@StringRes
fun BetFilter.textId() =
    when (this) {
        PUBLIC -> R.string.bet_public
        INVITATION -> R.string.bet_invitation
        IN_PROGRESS -> R.string.bet_current
        FINISHED -> R.string.bet_finished
    }