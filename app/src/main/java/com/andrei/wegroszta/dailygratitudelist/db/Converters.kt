package com.andrei.wegroszta.dailygratitudelist.db

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    private val format = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    @TypeConverter
    fun stringListFromString(value: String): List<String> = format.decodeFromString(value)

    @TypeConverter
    fun stringListToString(strings: List<String>): String = format.encodeToString(strings)
}
