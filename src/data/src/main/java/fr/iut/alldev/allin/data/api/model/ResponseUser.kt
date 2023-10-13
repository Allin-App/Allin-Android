package fr.iut.alldev.allin.data.api.model

import androidx.annotation.Keep
import fr.iut.alldev.allin.data.model.User
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ResponseUser(
    val username: String,
    val email: String,
    val password: String,
    var nbCoins: Int,
){
    fun toUser() = User(
        username = username,
        email = email,
        id = "",
        coins = nbCoins
    )
}

@Keep
@Serializable
data class CheckUser(
    val username: String,
    val password: String,
)