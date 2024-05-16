package fr.iut.alldev.allin.ui.navigation

import fr.iut.alldev.allin.R

sealed class TopLevelDestination(
    val route: String,
    val title: Int,
    val subtitle: Int,
    val emoji: Int,
) {
    data object PublicBets : TopLevelDestination(
        route = Routes.PUBLIC_BETS,
        title = R.string.drawer_public_bets,
        subtitle = R.string.drawer_public_bets_subtitle,
        emoji = R.drawable.globe
    )

    data object BetCreation : TopLevelDestination(
        route = Routes.BET_CREATION,
        title = R.string.drawer_create_bet,
        subtitle = R.string.drawer_create_bet_subtitle,
        emoji = R.drawable.video_game
    )

    data object BetHistory : TopLevelDestination(
        route = Routes.BET_HISTORY,
        title = R.string.drawer_bet_history,
        subtitle = R.string.drawer_bet_history_subtitle,
        emoji = R.drawable.eyes
    )

    data object Friends : TopLevelDestination(
        route = Routes.FRIENDS,
        title = R.string.drawer_friends,
        subtitle = R.string.drawer_friends_subtitle,
        emoji = R.drawable.holding_hands
    )

    data object CurrentBets : TopLevelDestination(
        route = Routes.BET_CURRENT,
        title = R.string.drawer_current_bets,
        subtitle = R.string.drawer_current_bets_subtitle,
        emoji = R.drawable.money_with_wings
    )

    data object Ranking : TopLevelDestination(
        route = Routes.RANKING,
        title = R.string.drawer_ranking,
        subtitle = R.string.drawer_ranking_subtitle,
        emoji = R.drawable.ranking
    )
}
