package com.andrei.wegroszta.dailygratitudelist.navigation

sealed class ScreenRoute(val route: String)

object GratitudeListScreenRoute : ScreenRoute("gratitudes")
object GratitudeDetailsScreenRoute : ScreenRoute("gratitudes")
