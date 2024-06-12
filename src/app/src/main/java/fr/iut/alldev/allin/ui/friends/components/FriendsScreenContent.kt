package fr.iut.alldev.allin.ui.friends.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
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
import kotlinx.coroutines.launch

@Composable
fun FriendsScreenContent(
    addTabState: FriendsScreenViewModel.AddTabState,
    search: String,
    isRefreshing: Boolean,
    onToggleDeleteFriend: (User) -> Unit,
    setSearch: (String) -> Unit,
    requestsTabState: FriendsScreenViewModel.RequestsTabState,
    acceptRequest: (User) -> Unit,
    declineRequest: (User) -> Unit,
    refresh: () -> Unit

) {
    val focus = LocalFocusManager.current
    val pullRefreshState = rememberPullRefreshState(isRefreshing, refresh)
    val progressAnimation by animateFloatAsState(pullRefreshState.progress * 15, label = "")
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .padding(top = with(LocalDensity.current) {
                progressAnimation.toDp()
            })
    ) {
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
                modifier = Modifier.let {
                    if (isRefreshing) {
                        it
                            .alpha(.5f)
                            .pointerInput(Unit) {
                                scope.launch {
                                    awaitPointerEventScope {
                                        while (true) {
                                            awaitPointerEvent(pass = PointerEventPass.Initial)
                                                .changes
                                                .forEach(PointerInputChange::consume)
                                        }
                                    }
                                }
                            }

                    } else it
                },
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
                    SectionElement(
                        stringResource(
                            id = R.string.friends_requests_tab,
                            (requestsTabState as? FriendsScreenViewModel.RequestsTabState.Loaded)
                                ?.users?.size ?: 0
                        )
                    ) {

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

        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = isRefreshing,
            state = pullRefreshState
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
            declineRequest = {},
            refresh = {},
            isRefreshing = false
        )
    }
}