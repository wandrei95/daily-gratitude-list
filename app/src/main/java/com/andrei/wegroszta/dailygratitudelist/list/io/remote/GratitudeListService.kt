package com.andrei.wegroszta.dailygratitudelist.list.io.remote

import retrofit2.http.GET

interface GratitudeListService {
    @GET("gratitudes")
    suspend fun loadGratitudes(): List<NetworkGratitude>
}
