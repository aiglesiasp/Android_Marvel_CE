package com.aiglepub.marvelcompose.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem (
    internal val feature: Feature,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArgs> = emptyList()
) {
    class ContentType(feature: Feature): NavItem(feature)
    class ContentTypeDetail(feature: Feature): NavItem(feature, "detail", listOf(NavArgs.ItemId)) {
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