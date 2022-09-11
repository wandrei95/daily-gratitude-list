package com.andrei.wegroszta.dailygratitudelist.list.io.remote

import com.andrei.wegroszta.dailygratitudelist.entities.Gratitude
import kotlinx.serialization.Serializable

@Serializable
data class NetworkGratitude(
    val id: String,
    val date: Long,
    val summary: String,
    val images: List<String>?,
    val tags: List<String>?
)

fun NetworkGratitude.toGratitude() = Gratitude(
    id = this.id,
    date = this.date,
    summary = this.summary,
    photoUrls = this.images ?: emptyList(),
    tags = this.tags ?: emptyList()
)