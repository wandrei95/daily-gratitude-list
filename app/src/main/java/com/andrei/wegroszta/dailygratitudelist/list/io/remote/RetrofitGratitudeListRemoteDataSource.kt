package com.andrei.wegroszta.dailygratitudelist.list.io.remote

import com.andrei.wegroszta.dailygratitudelist.entities.Gratitude
import com.andrei.wegroszta.dailygratitudelist.list.data.GratitudeListRepository.GratitudeListRemoteDataSource
import com.andrei.wegroszta.dailygratitudelist.networking.runWithNetworkErrorHandling
import javax.inject.Inject

class RetrofitGratitudeListRemoteDataSource @Inject constructor(
    private val service: GratitudeListService
) : GratitudeListRemoteDataSource {

    override suspend fun load(): List<Gratitude> = runWithNetworkErrorHandling {
        service.loadGratitudes().map { it.toGratitude() }
    }
}
