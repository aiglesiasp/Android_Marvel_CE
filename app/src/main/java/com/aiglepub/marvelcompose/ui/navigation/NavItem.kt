package com.aiglepub.marvelcompose.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.aiglepub.marvelcompose.R


enum class NavItem(
    val navCommand: NavCommand,
    val icon: ImageVector,
    @StringRes val title: Int
) {
    //Las 2 opciones del menu lateral
    HOME(NavCommand.ContentType(Feature.CHARACTERS), Icons.Default.Home, R.string.home),
    SETTINGS(NavCommand.ContentType(Feature.SETTINGS), Icons.Default.Settings, R.string.settings),

    //Las 3 iconos de la barra de navegacion de abajo
    CHARACTERS(NavCommand.ContentType(Feature.CHARACTERS), Icons.Default.Face, R.string.characters),
    COMICS(NavCommand.ContentType(Feature.COMICS), Icons.Default.Book, R.string.comics),
    EVENTS(NavCommand.ContentType(Feature.EVENTS), Icons.Default.Event, R.string.events),

}

sealed class NavCommand (
    internal val feature: Feature,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArgs> = emptyList()
) {
    class ContentType(feature: Feature): NavCommand(feature)
    class ContentTypeDetail(feature: Feature): NavCommand(feature, "detail", listOf(NavArgs.ItemId)) {
        fun createRoute(itemId: Int) = "${feature.route}/$subRoute/$itemId"
    }

    val route = run {
        // baseroute/{arg1}/{arg2}/...
        val argKeys = navArgs.map { "{${it.key}}" } //mapeamos en argKeys las claves añadiendo las llaves
        listOf(feature.route, subRoute)
            .plus(argKeys)
            .joinToString("/") //Creamos una lista con la baseRouter mas todos los argumentos y lo pasamos a String con el separador /
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType } //Con esto mapeamos los argumentos para pasarlo mas sencillo
    }
}

enum class NavArgs(val key: String, val navType: NavType<*>) {
    ItemId(key = "itemId", navType = NavType.IntType)
}