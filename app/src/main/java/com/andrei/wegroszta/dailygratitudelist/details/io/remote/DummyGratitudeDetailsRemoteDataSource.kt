package com.andrei.wegroszta.dailygratitudelist.details.io.remote

import com.andrei.wegroszta.dailygratitudelist.details.data.GratitudeDetailsRepository.GratitudeDetailsRemoteDataSource
import kotlinx.coroutines.delay

class DummyGratitudeDetailsRemoteDataSource : GratitudeDetailsRemoteDataSource {
    override suspend fun updateSummary(gratitudeId: String, summary: String) {
        delay(2000L)
    }

    override suspend fun delete(gratitudeId: String) {
        delay(2000L)
    }
}
