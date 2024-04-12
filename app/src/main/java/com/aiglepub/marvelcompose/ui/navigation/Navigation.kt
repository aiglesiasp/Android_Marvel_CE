package com.aiglepub.marvelcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aiglepub.marvelcompose.ui.screens.characters.CharactersScreen
import com.aiglepub.marvelcompose.ui.screens.detail.CharacterDetailScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavItem.Characters.route ) {

        composable(NavItem.Characters.route) {
            CharactersScreen(onClick = {character ->
                navController.navigate(NavItem.CharacterDetail.createRoute(character.id))
            })
        }

        composable(NavItem.CharacterDetail.route) {
            CharacterDetailScreen(it.findArgs<Int>(NavArgs.ItemId))
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
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
    val value = arguments?.getString(arg.key)
    requireNotNull(value)
    return value as T
}