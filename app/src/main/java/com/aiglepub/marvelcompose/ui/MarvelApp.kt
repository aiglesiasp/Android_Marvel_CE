package com.aiglepub.marvelcompose.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aiglepub.marvelcompose.R
import com.aiglepub.marvelcompose.ui.navigation.AppBottomNavigation
import com.aiglepub.marvelcompose.ui.navigation.ArrowBackIcon
import com.aiglepub.marvelcompose.ui.navigation.NavItem
import com.aiglepub.marvelcompose.ui.navigation.Navigation
import com.aiglepub.marvelcompose.ui.navigation.navigatePoppingUpToStartDestination
import com.aiglepub.marvelcompose.ui.theme.MarvelComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""
    val showUpNavigation = currentRoute !in NavItem.values().map { it.navCommand.route }

    MarvelScreen {
        Scaffold(
            topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(id = R.string.app_name)) },
                        navigationIcon = {
                            if(showUpNavigation) {
                                ArrowBackIcon(imageVector = Icons.Default.ArrowBack, onUpClick = { navController.popBackStack() })
                            } else null
                        }
                    )
            },
            bottomBar = {
                AppBottomNavigation(currentRoute = currentRoute, onNavItemClick = {navItem ->
                    navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
                })
            },

        ) {paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                Navigation(navController = navController)
            }
        }
    }
}

@Composable
fun MarvelScreen(content: @Composable () -> Unit) {
    MarvelComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

