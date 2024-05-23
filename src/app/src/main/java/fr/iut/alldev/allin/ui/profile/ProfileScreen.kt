package fr.iut.alldev.allin.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.iut.alldev.allin.ui.profile.components.ProfileScreenHeader

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column {
        when (val s = state) {
            is ProfileViewModel.State.Loaded -> {
                ProfileScreenHeader(
                    username = s.user.username,
                    totalBets = 333,
                    bestWin = 365,
                    friends = 3
                )
            }

            ProfileViewModel.State.Loading -> {

            }
        }
    }
}