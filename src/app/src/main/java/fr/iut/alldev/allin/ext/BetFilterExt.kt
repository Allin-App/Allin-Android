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
        PUBLIC -> R.string.Public
        INVITATION -> R.string.Invitation
        IN_PROGRESS -> R.string.Current
        FINISHED -> R.string.Finished
    }