package fr.iut.alldev.allin.ui.ranking

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import fr.iut.alldev.allin.data.model.User
import fr.iut.alldev.allin.ui.ranking.components.RankingScreenContent

@Composable
fun RankingScreen() {
    val users = remember { mockRanking }

    RankingScreenContent(
        users = users.sortedByDescending { it.coins }
    )
}

private val mockRanking by lazy {
    listOf(
        User(
            id = "1",
            username = "Owen",
            email = "",
            coins = 8533
        ),
        User(
            id = "2",
            username = "Dave",
            email = "",
            coins = 6942
        ),
        User(
            id = "3",
            username = "Lucas",
            email = "",
            coins = 3333
        ),
        User(
            id = "4",
            username = "Louison",
            email = "",
            coins = 1970
        ),
        User(
            id = "5",
            username = "Imri",
            email = "",
            coins = 1
        ),
        User(
            id = "21",
            username = "Owen",
            email = "",
            coins = 8533
        ),
        User(
            id = "22",
            username = "Dave",
            email = "",
            coins = 6942
        ),
        User(
            id = "23",
            username = "Lucas",
            email = "",
            coins = 3333
        ),
        User(
            id = "24",
            username = "Louison",
            email = "",
            coins = 1970
        ),
        User(
            id = "25",
            username = "Imri",
            email = "",
            coins = 1
        ),
        User(
            id = "31",
            username = "Owen",
            email = "",
            coins = 8533
        ),
        User(
            id = "32",
            username = "Dave",
            email = "",
            coins = 6942
        ),
        User(
            id = "33",
            username = "Lucas",
            email = "",
            coins = 3333
        ),
        User(
            id = "34",
            username = "Louison",
            email = "",
            coins = 1970
        ),
        User(
            id = "35",
            username = "Imri",
            email = "",
            coins = 1
        )
    )
}