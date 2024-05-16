package fr.iut.alldev.allin.ui.friends.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.ext.asPaddingValues
import fr.iut.alldev.allin.theme.AllInColorToken
import fr.iut.alldev.allin.theme.AllInTheme
import fr.iut.alldev.allin.ui.core.AllInTextField

@Composable
fun FriendsScreenContent(
    friends: List<User>,
    deleted: List<User>,
    search: String,
    onToggleDeleteFriend: (User) -> Unit,
    setSearch: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = WindowInsets.navigationBars.asPaddingValues(start = 24.dp, end = 24.dp, top = 18.dp),
        verticalArrangement = Arrangement.spacedBy(11.dp),
    ) {
        item {
            Text(
                text = stringResource(id = R.string.friends_title),
                style = AllInTheme.typography.h1,
                color = AllInColorToken.allInGrey,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        stickyHeader {
            AllInTextField(
                value = search,
                onValueChange = setSearch,
                leadingIcon = rememberVectorPainter(image = Icons.Default.Search),
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            0.5f to AllInTheme.colors.mainSurface,
                            1f to Color.Transparent
                        )
                    )
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        items(friends) {
            FriendsScreenLine(
                username = it.username,
                isFriend = it !in deleted,
                toggleIsFriend = { onToggleDeleteFriend(it) }
            )
        }

    }
}

@Preview
@Composable
private fun FriendsScreenContentPreview() {
    AllInTheme {
        FriendsScreenContent(
            friends = emptyList(),
            deleted = emptyList(),
            search = "",
            setSearch = {},
            onToggleDeleteFriend = {}
        )
    }
}