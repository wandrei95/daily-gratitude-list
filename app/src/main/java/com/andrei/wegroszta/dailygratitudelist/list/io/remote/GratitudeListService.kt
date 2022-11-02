package com.andrei.wegroszta.dailygratitudelist.list.io.remote

import retrofit2.http.GET

interface GratitudeListService {
    @GET("gratitudesssssssss")
    suspend fun loadGratitudes(): List<NetworkGratitude>
}
