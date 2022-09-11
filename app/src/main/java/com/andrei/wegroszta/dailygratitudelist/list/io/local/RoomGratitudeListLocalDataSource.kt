package com.andrei.wegroszta.dailygratitudelist.list.io.local

import com.andrei.wegroszta.dailygratitudelist.db.GratitudeDao
import com.andrei.wegroszta.dailygratitudelist.db.toGratitude
import com.andrei.wegroszta.dailygratitudelist.db.toGratitudeEntity
import com.andrei.wegroszta.dailygratitudelist.entities.Gratitude
import com.andrei.wegroszta.dailygratitudelist.list.data.GratitudeListRepository.GratitudeListLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomGratitudeListLocalDataSource @Inject constructor(
    private val dao: GratitudeDao
) : GratitudeListLocalDataSource {

    override fun getGratitudes(): Flow<List<Gratitude>> {
        return dao.getGratitudesFlow().map { list ->
            list.map { gratitudeEntity ->
                gratitudeEntity.toGratitude()
            }
        }
    }

    override suspend fun save(gratitudes: List<Gratitude>) {
        dao.insertGratitudes(gratitudes.map { it.toGratitudeEntity() })
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }
}
