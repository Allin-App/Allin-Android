package fr.iut.alldev.allin.data.model

data class User(
    val id: String,
    val username: String,
    val email: String,
    val coins: Int,
    var nbBets: Int,
    var nbFriends: Int,
    var bestWin: Int,
    val friendStatus: FriendStatus? = null,
    val image: String? = null
)