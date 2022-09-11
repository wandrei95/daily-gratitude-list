package com.andrei.wegroszta.dailygratitudelist.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andrei.wegroszta.dailygratitudelist.entities.Gratitude

@Entity(tableName = "gratitude")
data class GratitudeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val date: Long,
    val summary: String,
    val photoUrls: List<String>,
    val tags: List<String>
)

fun GratitudeEntity.toGratitude() = Gratitude(
    id = this.id,
    date = this.date,
    summary = this.summary,
    photoUrls = this.photoUrls,
    tags = this.tags
)

fun Gratitude.toGratitudeEntity() = GratitudeEntity(
    id = this.id,
    date = this.date,
    summary = this.summary,
    photoUrls = this.photoUrls,
    tags = this.tags
)
