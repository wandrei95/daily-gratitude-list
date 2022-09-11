package com.andrei.wegroszta.dailygratitudelist.details.io.remote

import com.andrei.wegroszta.dailygratitudelist.details.data.GratitudeDetailsRepository.GratitudeDetailsRemoteDataSource
import javax.inject.Inject

class RetrofitGratitudeDetailsRemoteDataSource @Inject constructor(
    private val service: GratitudeDetailsService
) : GratitudeDetailsRemoteDataSource {

    override suspend fun updateSummary(gratitudeId: String, summary: String) {
        service.updateSummary(gratitudeId, GratitudeDetailsService.UpdateRequest(summary))
    }

    override suspend fun delete(gratitudeId: String) {
        service.delete(gratitudeId)
    }

}
