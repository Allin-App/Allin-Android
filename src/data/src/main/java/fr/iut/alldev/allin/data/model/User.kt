package fr.iut.alldev.allin.data.model

data class User(
    val id: String,
    val username: String,
    val email: String,
    val coins: Int,
    val friendStatus: FriendStatus? = null
)