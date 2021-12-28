package org.romeo.layer_data.api

import okhttp3.Interceptor
import okhttp3.Response
import org.romeo.layer_data.exceptions.NoAuthException
import org.romeo.model.exceptions.UserAlreadyExistsException
import org.romeo.layer_data.data_sources.preferences.TokenUserDataSourceLocal

class MainInterceptor(
    private val responseDataSource: TokenUserDataSourceLocal
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = responseDataSource.get()?.token

        val newRequest = token?.let {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } ?: chain.request()

        val response = chain.proceed(newRequest)
        val responseCode = response.code()
        val responseMessage = response.message()
        processResponseCode(responseCode, responseMessage)
        return response
    }

    private fun processResponseCode(responseCode: Int, responseMessage: String) {
        when (responseCode) {
            401 -> throw NoAuthException(responseMessage)
            409 -> throw UserAlreadyExistsException()
        }
    }
}