package com.andrei.wegroszta.dailygratitudelist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [GratitudeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class GratitudeDatabase : RoomDatabase() {
    abstract fun getGratitudeDao(): GratitudeDao
}
