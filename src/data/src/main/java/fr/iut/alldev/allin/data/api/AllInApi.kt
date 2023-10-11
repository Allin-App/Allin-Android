package fr.iut.alldev.allin.data.api

import fr.iut.alldev.allin.data.api.model.CheckUser
import fr.iut.alldev.allin.data.api.model.ResponseUser
import retrofit2.http.Body
import retrofit2.http.POST


interface AllInApi {
    @POST("users/login")
    suspend fun login(
        @Body body: CheckUser
    ): ResponseUser

    @POST("users/register")
    suspend fun register(
        @Body body: ResponseUser
    ): ResponseUser
}