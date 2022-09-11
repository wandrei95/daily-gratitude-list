package com.andrei.wegroszta.dailygratitudelist.entities

data class Gratitude(
    val id: String,
    val date: Long,
    val summary: String,
    val photoUrls: List<String>,
    val tags: List<String>
)
