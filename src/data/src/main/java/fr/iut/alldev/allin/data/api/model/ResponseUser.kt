package fr.iut.alldev.allin.data.api.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ResponseUser(
    val username: String,
    val email: String,
    val password: String,
    var nbCoins: Int,
)

@Keep
@Serializable
data class CheckUser(
    val username: String,
    val password: String,
)