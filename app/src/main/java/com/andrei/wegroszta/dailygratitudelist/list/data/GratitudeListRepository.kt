package com.andrei.wegroszta.dailygratitudelist.list.data

import com.andrei.wegroszta.dailygratitudelist.entities.Gratitude
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GratitudeListRepository @Inject constructor(
    private val remoteDataSource: GratitudeListRemoteDataSource,
    private val localDataSource: GratitudeListLocalDataSource
) {

    fun getGratitudeListFlow(): Flow<List<Gratitude>> = localDataSource.getGratitudes()

    suspend fun loadGratitudes() = coroutineScope {
        val newGratitudesDef = async {
            remoteDataSource.load()
        }
        val deleteJob = launch {
            localDataSource.deleteAll()
        }

        val newGratitudes = newGratitudesDef.await()
        deleteJob.join()

        localDataSource.save(newGratitudes)
    }

    interface GratitudeListRemoteDataSource {
        suspend fun load(): List<Gratitude>
    }

    interface GratitudeListLocalDataSource {
        fun getGratitudes(): Flow<List<Gratitude>>
        suspend fun save(gratitudes: List<Gratitude>)
        suspend fun deleteAll()
    }
}
