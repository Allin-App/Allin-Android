package fr.iut.alldev.allin.ui.friends.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInLoading
import fr.iut.alldev.allin.ui.core.AllInSections
import fr.iut.alldev.allin.ui.core.SectionElement
import fr.iut.alldev.allin.ui.friends.FriendsScreenViewModel
import fr.iut.alldev.allin.ui.friends.tabs.FriendsScreenAddTab
import fr.iut.alldev.allin.ui.friends.tabs.FriendsScreenRequestsTab

@Composable
fun FriendsScreenContent(
    addTabState: FriendsScreenViewModel.AddTabState,
    search: String,
    onToggleDeleteFriend: (User) -> Unit,
    setSearch: (String) -> Unit,
    requestsTabState: FriendsScreenViewModel.RequestsTabState,
    acceptRequest: (User) -> Unit,
    declineRequest: (User) -> Unit

) {
    val focus = LocalFocusManager.current

    Column(Modifier.fillMaxSize()) {

        Text(
            text = stringResource(id = R.string.friends_title),
            style = AllInTheme.typography.h1,
            color = AllInColorToken.allInGrey,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 11.dp)
        )

        AllInSections(
            onLoadSection = { focus.clearFocus() },
            sections = listOf(
                SectionElement(stringResource(id = R.string.friends_add_tab)) {
                    when (addTabState) {
                        is FriendsScreenViewModel.AddTabState.Loaded -> {
                            FriendsScreenAddTab(
                                friends = addTabState.users,
                                search = search,
                                setSearch = setSearch,
                                onToggleDeleteFriend = onToggleDeleteFriend
                            )
                        }

                        FriendsScreenViewModel.AddTabState.Loading -> {
                            AllInLoading(visible = true)
                        }
                    }
                },
                SectionElement(stringResource(id = R.string.friends_requests_tab)) {

                    when (requestsTabState) {
                        is FriendsScreenViewModel.RequestsTabState.Loaded -> {
                            FriendsScreenRequestsTab(
                                requests = requestsTabState.users,
                                acceptRequest = acceptRequest,
                                declineRequest = declineRequest
                            )
                        }

                        FriendsScreenViewModel.RequestsTabState.Loading -> {
                            AllInLoading(visible = true)
                        }
                    }
                }
            )
        )
    }
}

@Preview
@Composable
private fun FriendsScreenContentPreview() {
    AllInTheme {
        FriendsScreenContent(
            addTabState = FriendsScreenViewModel.AddTabState.Loaded(emptyList()),
            search = "",
            setSearch = {},
            onToggleDeleteFriend = {},
            requestsTabState = FriendsScreenViewModel.RequestsTabState.Loaded(emptyList()),
            acceptRequest = {},
            declineRequest = {}
        )
    }
}