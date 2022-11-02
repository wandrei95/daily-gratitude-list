package com.andrei.wegroszta.dailygratitudelist

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.andrei.wegroszta.dailygratitudelist.navigation.Navigation
import com.andrei.wegroszta.dailygratitudelist.theme.DailyGratitudeListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyGratitudeListTheme {
                Navigation()

            }
        }
    }
}
