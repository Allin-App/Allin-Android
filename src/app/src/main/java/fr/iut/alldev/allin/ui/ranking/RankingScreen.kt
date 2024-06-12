package fr.iut.alldev.allin.ui.ranking

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.iut.alldev.allin.ui.core.AllInLoading
import fr.iut.alldev.allin.ui.ranking.components.RankingScreenContent

@Composable
fun RankingScreen(
    viewModel: RankingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val s = state) {
        is RankingViewModel.State.Loaded -> {
            RankingScreenContent(
                users = s.friends.sortedByDescending { it.coins }
            )
        }

        RankingViewModel.State.Loading -> {
            AllInLoading(visible = true)
        }
    }
}