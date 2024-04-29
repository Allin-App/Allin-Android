package fr.iut.alldev.allin.data.api

import fr.iut.alldev.allin.data.api.model.CheckUser
import fr.iut.alldev.allin.data.api.model.RequestBet
import fr.iut.alldev.allin.data.api.model.RequestParticipation
import fr.iut.alldev.allin.data.api.model.RequestUser
import fr.iut.alldev.allin.data.api.model.ResponseBet
import fr.iut.alldev.allin.data.api.model.ResponseBetDetail
import fr.iut.alldev.allin.data.api.model.ResponseBetResultDetail
import fr.iut.alldev.allin.data.api.model.ResponseUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AllInApi {
    companion object {
        fun String.formatBearerToken() = "Bearer $this"
    }

    @POST("users/login")
    suspend fun login(@Body body: CheckUser): ResponseUser

    @POST("users/register")
    suspend fun register(@Body body: RequestUser): ResponseUser

    @GET("users/token")
    suspend fun login(@Header("Authorization") token: String): ResponseUser

    @POST("bets/add")
    suspend fun createBet(@Header("Authorization") token: String, @Body body: RequestBet)

    @GET("users/gift")
    suspend fun dailyGift(@Header("Authorization") token: String): Int

    @GET("bets/gets")
    suspend fun getAllBets(@Header("Authorization") token: String): List<ResponseBet>

    @GET("bets/toConfirm")
    suspend fun getToConfirm(@Header("Authorization") token: String): List<ResponseBetDetail>

    @POST("bets/confirm/{id}")
    suspend fun confirmBet(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body value: String
    )

    @GET("betdetail/get/{id}")
    suspend fun getBet(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): ResponseBetDetail

    @GET("bets/getCurrent")
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

    @POST("participations/add")
    suspend fun participateToBet(
        @Header("Authorization") token: String,
        @Body body: RequestParticipation
    )
}