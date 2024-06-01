package fr.iut.alldev.allin.data.api.model

import androidx.annotation.Keep
import fr.iut.alldev.allin.data.model.FriendStatus
import fr.iut.alldev.allin.data.model.User
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class RequestUser(
    val username: String,
    val email: String,
    val password: String
)

@Keep
@Serializable
data class ResponseUser(
    val id: String = "",
    val username: String,
    val email: String,
    var nbCoins: Int,
    var token: String? = null,
    var nbBets: Int,
    var nbFriends: Int,
    var bestWin: Int,
    val friendStatus: FriendStatus? = null
) {
    fun toUser() = User(
        id = id,
        username = username,
        email = email,
        coins = nbCoins,
        friendStatus = friendStatus,
        nbBets = nbBets,
        nbFriends = nbFriends,
        bestWin = bestWin
    )
}

@Keep
@Serializable
data class CheckUser(
    val login: String,
    val password: String,
)