package com.andrei.wegroszta.dailygratitudelist.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrei.wegroszta.dailygratitudelist.entities.GratitudeException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class ErrorHandlingViewModel : ViewModel() {
    fun launchWithErrorHandling(action: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch {
            try {
                action()
            } catch (ex: Exception) {
                when (ex) {
                    is CancellationException -> throw ex
                    //todo catch different types of domain exception and threat them accordingly
                    is GratitudeException -> onError(ex)
                    else -> {
                        //todo
                    }
                }
            }
        }
    }

    open fun onError(exception: GratitudeException) {}
}
