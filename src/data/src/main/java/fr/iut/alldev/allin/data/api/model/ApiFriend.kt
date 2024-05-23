package fr.iut.alldev.allin.data.api.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class RequestFriend(val username: String)