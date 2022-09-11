package com.andrei.wegroszta.dailygratitudelist.details.io.local

import com.andrei.wegroszta.dailygratitudelist.db.GratitudeDao
import com.andrei.wegroszta.dailygratitudelist.db.toGratitude
import com.andrei.wegroszta.dailygratitudelist.details.data.GratitudeDetailsRepository.GratitudeDetailsLocalDataSource
import com.andrei.wegroszta.dailygratitudelist.entities.Gratitude
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomGratitudeDetailsLocalDataSource @Inject constructor(
    private val dao: GratitudeDao
) : GratitudeDetailsLocalDataSource {

    override fun getGratitude(id: String): Flow<Gratitude> =
        dao.getGratitudeFlow(id).map { it.toGratitude() }

    override suspend fun updateSummary(gratitudeId: String, summary: String) {
        dao.updateSummary(gratitudeId, summary)
    }

    override suspend fun delete(gratitudeId: String) {
        dao.delete(gratitudeId)
    }
}
