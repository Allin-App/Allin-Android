package fr.iut.alldev.allin.data.api

import fr.iut.alldev.allin.data.api.model.CheckUser
import fr.iut.alldev.allin.data.api.model.RequestBet
import fr.iut.alldev.allin.data.api.model.RequestUser
import fr.iut.alldev.allin.data.api.model.ResponseBet
import fr.iut.alldev.allin.data.api.model.ResponseUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AllInApi {
    @POST("users/login")
    suspend fun login(@Body body: CheckUser): ResponseUser

    @POST("users/register")
    suspend fun register(@Body body: RequestUser): ResponseUser

    @GET("users/token")
    suspend fun login(@Header("Authorization") token: String): ResponseUser

    @POST("bets/add")
    suspend fun createBet(@Body body: RequestBet)

    @GET("bets/gets")
    suspend fun getAllBets(): List<ResponseBet>
}