package fr.iut.alldev.allin.data.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

open class AllInAPIException(message: String) : IOException(message)
class AllInNotFoundException(message: String) : AllInAPIException(message)
class AllInUnauthorizedException(message: String) : AllInAPIException(message)
class AllInUnsuccessfulException(message: String) : AllInAPIException(message)


class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if(!response.isSuccessful){
            when (response.code) {
                404 -> throw AllInNotFoundException(response.message)
                401 -> throw AllInUnauthorizedException(response.message)
                else -> throw AllInUnsuccessfulException(response.message)
            }
        }
        if (response.body?.contentType()?.subtype != "json") {
            throw AllInAPIException(response.message)
        }

        return response
    }
}