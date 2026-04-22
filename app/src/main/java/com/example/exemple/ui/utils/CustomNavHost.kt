package com.example.exemple.ui.utils


import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

private const val TRANSITION_DURATION_MS = 200
private const val SLIDE_OFFSET_PX = 500

@Composable
fun CustomNavHost(
    navController: NavHostController,
    startDestination: String,
    builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                initialOffset = { SLIDE_OFFSET_PX },
                animationSpec = tween(TRANSITION_DURATION_MS)
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(TRANSITION_DURATION_MS)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                initialOffset = { -SLIDE_OFFSET_PX },
                animationSpec = tween(TRANSITION_DURATION_MS)
            )
        },
        popExitTransition = {
            fadeOut(
                animationSpec = tween(TRANSITION_DURATION_MS)
            )
        },
        builder = builder
    )
}