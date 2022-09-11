package com.andrei.wegroszta.dailygratitudelist.entities

open class GratitudeException(
    message: String,
    cause: Throwable
) : RuntimeException(message, cause)
