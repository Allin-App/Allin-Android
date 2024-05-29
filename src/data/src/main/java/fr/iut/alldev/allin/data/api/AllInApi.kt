package fr.iut.alldev.allin.data.api

import fr.iut.alldev.allin.data.api.model.CheckUser
import fr.iut.alldev.allin.data.api.model.RequestBet
import fr.iut.alldev.allin.data.api.model.RequestBetFilters
import fr.iut.alldev.allin.data.api.model.RequestFriend
import fr.iut.alldev.allin.data.api.model.RequestParticipation
import fr.iut.alldev.allin.data.api.model.RequestUser
import fr.iut.alldev.allin.data.api.model.ResponseBet
import fr.iut.alldev.allin.data.api.model.ResponseBetDetail
import fr.iut.alldev.allin.data.api.model.ResponseBetResultDetail
import fr.iut.alldev.allin.data.api.model.ResponseUser
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AllInApi {
    companion object {
        fun String.formatBearerToken() = "Bearer $this"

        fun String.asRequestBody() =
            this.toRequestBody("text/plain".toMediaTypeOrNull())
    }

    // USERS
    // ---------------------

    @POST("users/login")
    suspend fun login(@Body body: CheckUser): ResponseUser

    @POST("users/register")
    suspend fun register(@Body body: RequestUser): ResponseUser

    @GET("users/token")
    suspend fun login(@Header("Authorization") token: String): ResponseUser

    @GET("users/gift")
    suspend fun dailyGift(@Header("Authorization") token: String): Int

    // FRIENDS
    // ---------------------

    @GET("friends/gets")
    suspend fun getFriends(@Header("Authorization") token: String): List<ResponseUser>

    @POST("friends/add")
    suspend fun addFriend(
        @Header("Authorization") token: String,
        @Body request: RequestFriend
    )

    @POST("friends/delete")
    suspend fun deleteFriend(
        @Header("Authorization") token: String,
        @Body request: RequestFriend
    )

    @GET("friends/search/{search}")
    suspend fun searchFriend(
        @Header("Authorization") token: String,
        @Path("search") search: String
    ): List<ResponseUser>

    // BETS
    // ---------------------

    @POST("bets/add")
    suspend fun createBet(@Header("Authorization") token: String, @Body body: RequestBet)

    @POST("bets/gets")
    suspend fun getAllBets(
        @Header("Authorization") token: String,
        @Body body: RequestBetFilters
    ): List<ResponseBet>

    @GET("bets/popular")
    suspend fun getPopularBet(@Header("Authorization") token: String): ResponseBet?

    @GET("bets/toConfirm")
    suspend fun getToConfirm(@Header("Authorization") token: String): List<ResponseBetDetail>

    @POST("bets/confirm/{id}")
    suspend fun confirmBet(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body value: RequestBody
    )

    @GET("bets/current")
    suspend fun getBetCurrent(
        @Header("Authorization") token: String
    ): List<ResponseBetDetail>

    @GET("bets/history")
    suspend fun getBetHistory(
        @Header("Authorization") token: String
    ): List<ResponseBetResultDetail>

    @GET("bets/getWon")
    suspend fun getWon(
        @Header("Authorization") token: String
    ): List<ResponseBetResultDetail>

    @GET("betdetail/get/{id}")
    suspend fun getBet(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): ResponseBetDetail

    // PARTICIPATIONS
    // ---------------------

    @POST("participations/add")
    suspend fun participateToBet(
        @Header("Authorization") token: String,
        @Body body: RequestParticipation
    )
}