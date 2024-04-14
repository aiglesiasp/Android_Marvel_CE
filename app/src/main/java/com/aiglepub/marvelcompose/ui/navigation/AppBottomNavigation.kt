package com.aiglepub.marvelcompose.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun AppBottomNavigation(currentRoute: String, onNavItemClick: (NavItem) -> Unit) {
    NavigationBar {
        NavItem.entries.forEach {navItem ->
            val title = stringResource(id = navItem.title)
            NavigationBarItem(
                selected = currentRoute.contains(navItem.navCommand.feature.route),
                onClick = { onNavItemClick(navItem) },
                icon = { Icon(imageVector = navItem.icon, contentDescription = title) },
                label = { Text(text = title) }
            )
        }
    }
}