package com.aiglepub.marvelcompose.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material3.DrawerValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aiglepub.marvelcompose.R
import com.aiglepub.marvelcompose.ui.navigation.AppBarIcon
import com.aiglepub.marvelcompose.ui.navigation.AppBottomNavigation
import com.aiglepub.marvelcompose.ui.navigation.ArrowBackIcon
import com.aiglepub.marvelcompose.ui.navigation.NavItem
import com.aiglepub.marvelcompose.ui.navigation.Navigation
import com.aiglepub.marvelcompose.ui.navigation.navigatePoppingUpToStartDestination
import com.aiglepub.marvelcompose.ui.theme.MarvelComposeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""
    val showUpNavigation = currentRoute !in NavItem.values().map { it.navCommand.route }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerOptions = listOf(NavItem.HOME, NavItem.SETTINGS)

    MarvelScreen {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Text(text = "Marvel Compose", modifier = Modifier.padding(16.dp))
                    Divider(modifier = Modifier.height(2.dp).fillMaxWidth().background(Color.Black))
                    drawerOptions.forEach {
                        NavigationDrawerItem(
                            label = { Text(text = it.name) },
                            selected = false,
                            onClick = { /*TODO*/ }
                        )
                    }
                }
            },
            gesturesEnabled = true
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(id = R.string.app_name)) },
                        navigationIcon = {
                            if(showUpNavigation) {
                                ArrowBackIcon(
                                    imageVector = Icons.Default.ArrowBack,
                                    onUpClick = { navController.popBackStack() }
                                )
                            } else {
                                IconButton(onClick = { scope.launch {drawerState.open()} }) {
                                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                                }
                            }
                        }
                    )
                },
                bottomBar = {
                    AppBottomNavigation(
                        currentRoute = currentRoute,
                        onNavItemClick = { navItem ->
                            navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
                        }
                    )
                }

            ) {paddingValues ->
                Box(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    Navigation(navController = navController)
                }
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

