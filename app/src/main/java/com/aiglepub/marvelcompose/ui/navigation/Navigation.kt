package com.aiglepub.marvelcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.aiglepub.marvelcompose.ui.screens.characters.CharactersScreen
import com.aiglepub.marvelcompose.ui.screens.detail.CharacterDetailScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTERS.route
    ) {
       charactersNav(navController)
    }
}

private fun NavGraphBuilder.charactersNav(
    navController: NavController
) {
    // NAVEGACION DEL CHARACTER
    navigation(
        startDestination = NavItem.ContentType(Feature.CHARACTERS).route,
        route = Feature.CHARACTERS.route
    ) {
        composable(NavItem.ContentType(Feature.CHARACTERS)) {
            CharactersScreen(onClick = {character ->
                navController.navigate(NavItem.ContentDetail(Feature.CHARACTERS).createRoute(character.id))
            })
        }
        composable(NavItem.ContentDetail(Feature.CHARACTERS)) {
            val id = it.findArgs<Int>(NavArgs.ItemId)
            CharacterDetailScreen(id = id, onUpClick = {
                navController.popBackStack()
            })
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
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}