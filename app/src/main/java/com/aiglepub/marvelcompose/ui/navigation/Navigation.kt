package com.aiglepub.marvelcompose.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.aiglepub.marvelcompose.ui.screens.characters.CharacterDetailScreen
import com.aiglepub.marvelcompose.ui.screens.characters.CharactersScreen
import com.aiglepub.marvelcompose.ui.screens.comics.ComicDetailScreen
import com.aiglepub.marvelcompose.ui.screens.comics.ComicsScreen
import com.aiglepub.marvelcompose.ui.screens.events.EventDetailScreen
import com.aiglepub.marvelcompose.ui.screens.events.EventsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTERS.route
    ) {
        charactersNav(navController)
        comicsNav(navController)
        eventsNav(navController)
    }
}

private fun NavGraphBuilder.charactersNav(
    navController: NavController
) {
    // NAVEGACION DEL CHARACTER
    navigation(
        startDestination = NavCommand.ContentType(Feature.CHARACTERS).route,
        route = Feature.CHARACTERS.route
    ) {
        composable(NavCommand.ContentType(Feature.CHARACTERS)) {
            CharactersScreen(onClick = {character ->
                navController.navigate(NavCommand.ContentTypeDetail(Feature.CHARACTERS).createRoute(character.id))
            })
        }
        composable(NavCommand.ContentTypeDetail(Feature.CHARACTERS)) {
            val id = it.findArgs<Int>(NavArgs.ItemId)
            CharacterDetailScreen(characterId = id, onUpClick = {
                navController.popBackStack()
            })
        }
    }
}

private fun NavGraphBuilder.comicsNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavCommand.ContentType(Feature.COMICS)) {
            ComicsScreen(
                onClick = { comic ->
                    navController.navigate(
                        NavCommand.ContentTypeDetail(Feature.COMICS).createRoute(comic.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentTypeDetail(Feature.COMICS)) {
            val id = it.findArgs<Int>(NavArgs.ItemId)
            ComicDetailScreen(
                comicId = id,
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun NavGraphBuilder.eventsNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.EVENTS).route,
        route = Feature.EVENTS.route
    ) {
        composable(NavCommand.ContentType(Feature.EVENTS)) {
            EventsScreen(
                onClick = { event ->
                    navController.navigate(
                        NavCommand.ContentTypeDetail(Feature.EVENTS).createRoute(event.id)
                    )
                }
            )
        }

        composable(NavCommand.ContentTypeDetail(Feature.EVENTS)) {
            val id = it.findArgs<Int>(NavArgs.ItemId)
            EventDetailScreen(
                eventId = id,
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}


private fun NavGraphBuilder.composable(
    navItem: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArgs(arg: NavArgs): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}