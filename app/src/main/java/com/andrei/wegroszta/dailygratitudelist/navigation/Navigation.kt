package com.andrei.wegroszta.dailygratitudelist.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andrei.wegroszta.dailygratitudelist.details.ui.GratitudeDetailsScreen
import com.andrei.wegroszta.dailygratitudelist.list.ui.GratitudeListScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = GratitudeListScreenRoute.route
    ) {
        composable(route = GratitudeListScreenRoute.route) {
            GratitudeListScreen { id ->
                navController.navigate("${GratitudeDetailsScreenRoute.route}/$id")
            }
        }
        composable(route = "${GratitudeDetailsScreenRoute.route}/{id}") { entry ->
            val id = entry.arguments?.getString("id")
                ?: throw IllegalStateException(
                    "no id argument passed for ${GratitudeDetailsScreenRoute.route}"
                )
            GratitudeDetailsScreen(
                gratitudeId = id,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
