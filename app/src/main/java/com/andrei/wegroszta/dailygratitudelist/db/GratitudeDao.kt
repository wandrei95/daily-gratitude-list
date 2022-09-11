package com.andrei.wegroszta.dailygratitudelist.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GratitudeDao {
    @Query("SELECT * FROM gratitude")
    fun getGratitudesFlow(): Flow<List<GratitudeEntity>>

    @Query("SELECT * FROM gratitude WHERE id = :id LIMIT 1")
    fun getGratitudeFlow(id: String): Flow<GratitudeEntity>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGratitudes(gratitudes: List<GratitudeEntity>)

    @Query("UPDATE gratitude SET summary = :summary WHERE id = :id")
    suspend fun updateSummary(id: String, summary: String)

    @Transaction
    @Query("DELETE FROM gratitude")
    suspend fun deleteAll()

    @Query("DELETE FROM gratitude WHERE id = :id")
    suspend fun delete(id: String)
}
