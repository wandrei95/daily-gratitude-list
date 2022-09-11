package com.andrei.wegroszta.dailygratitudelist.details.io.remote

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface GratitudeDetailsService {
    @GET("gratitudes/{id}")
    suspend fun updateSummary(@Path("id") id: String, @Body body: UpdateRequest)

    @DELETE("gratitudes/{id}")
    suspend fun delete(@Path("id") id: String)

    data class UpdateRequest(
        val summary: String
    )
}
