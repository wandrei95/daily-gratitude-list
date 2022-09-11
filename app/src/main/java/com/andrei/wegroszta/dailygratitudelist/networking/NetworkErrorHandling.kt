package com.andrei.wegroszta.dailygratitudelist.networking

import com.andrei.wegroszta.dailygratitudelist.entities.GratitudeException
import kotlinx.coroutines.CancellationException

suspend fun <T> runWithNetworkErrorHandling(action: suspend () -> T): T {
    try {
        return action()
    } catch (ex: Exception) {
        if (ex is CancellationException) {
            throw ex
        }

        //todo parse the exception and throw some more complex exception hierarchy
        throw GratitudeException(ex.message ?: "unknown error", ex)

    }
}
