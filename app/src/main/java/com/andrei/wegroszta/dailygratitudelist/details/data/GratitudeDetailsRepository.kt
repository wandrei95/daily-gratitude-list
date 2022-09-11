package com.andrei.wegroszta.dailygratitudelist.details.data

import com.andrei.wegroszta.dailygratitudelist.entities.Gratitude
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GratitudeDetailsRepository @Inject constructor(
    private val remoteDataSource: GratitudeDetailsRemoteDataSource,
    private val localDataSource: GratitudeDetailsLocalDataSource,
) {

    fun getGratitudeFlow(gratitudeId: String): Flow<Gratitude> =
        localDataSource.getGratitude(gratitudeId)

    suspend fun updateSummary(gratitudeId: String, summary: String) {
        remoteDataSource.updateSummary(gratitudeId, summary)
        localDataSource.updateSummary(gratitudeId, summary)
    }

    suspend fun delete(gratitudeId: String) {
        remoteDataSource.delete(gratitudeId)
        localDataSource.delete(gratitudeId)
    }

    interface GratitudeDetailsRemoteDataSource {
        suspend fun updateSummary(gratitudeId: String, summary: String)
        suspend fun delete(gratitudeId: String)
    }

    interface GratitudeDetailsLocalDataSource {
        fun getGratitude(id: String): Flow<Gratitude>
        suspend fun updateSummary(gratitudeId: String, summary: String)
        suspend fun delete(gratitudeId: String)
    }
}
