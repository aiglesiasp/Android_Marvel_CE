package com.aiglepub.marvelcompose.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aiglepub.marvelcompose.ui.navigation.Navigation
import com.aiglepub.marvelcompose.ui.theme.MarvelComposeTheme


@Composable
fun MarvelApp() {
    MarvelScreen {
        Scaffold(
            bottomBar = {

            }
        ) {paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                Navigation()
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