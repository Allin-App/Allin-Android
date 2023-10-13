package fr.iut.alldev.allin.ui.navigation

import fr.iut.alldev.allin.R

sealed class TopLevelDestination(
    val route: String,
    val title: Int,
    val subtitle: Int,
    val emoji: Int
) {
    object PUBLIC_BETS : TopLevelDestination(
        route = Routes.PUBLIC_BETS,
        title = R.string.public_bets,
        subtitle = R.string.public_bets_subtitle,
        emoji = R.drawable.globe
    )
    object BET_CREATION : TopLevelDestination(
        route = Routes.BET_CREATION,
        title = R.string.create_a_bet,
        subtitle = R.string.create_a_bet_subtitle,
        emoji = R.drawable.video_game
    )
    object BET_HISTORY : TopLevelDestination(
        route = Routes.BET_HISTORY,
        title = R.string.bet_history,
        subtitle = R.string.bet_history_subtitle,
        emoji = R.drawable.eyes
    )
    object FRIENDS : TopLevelDestination(
        route = Routes.FRIENDS,
        title = R.string.friends,
        subtitle = R.string.friends_subtitle,
        emoji = R.drawable.holding_hands
    )
    object CURRENT_BETS : TopLevelDestination(
        route = Routes.CURRENT_BETS,
        title = R.string.current_bets,
        subtitle = R.string.current_bets_subtitle,
        emoji = R.drawable.money_with_wings
    )
}
