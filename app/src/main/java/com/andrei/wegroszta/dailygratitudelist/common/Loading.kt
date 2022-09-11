package com.andrei.wegroszta.dailygratitudelist.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.andrei.wegroszta.dailygratitudelist.theme.FullScreenLoadingBackground

@Composable
fun FullScreenLoading(
    isLoading: Boolean,
    content: @Composable () -> Unit
) {
    if (isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = FullScreenLoadingBackground
                )
        ) {
            CircularProgressIndicator()
        }
        return
    }

    content()
}
