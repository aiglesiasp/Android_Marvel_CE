package com.aiglepub.marvelcompose.ui.screens.commons

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ShareCompat
import com.aiglepub.marvelcompose.data.entities.MarvelItem
import com.aiglepub.marvelcompose.data.entities.Url
import com.aiglepub.marvelcompose.ui.navigation.AppBarIcon
import com.aiglepub.marvelcompose.ui.navigation.ArrowBackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelItemDetailScaffold(
    marvelItem: MarvelItem,
    onUpClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = LocalContext.current
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = marvelItem.title) },
                navigationIcon = {
                    ArrowBackIcon(
                        imageVector = Icons.Default.ArrowBack,
                        onUpClick = { onUpClick() }
                    )
                },
                actions = { AppBarOverflowMenu(marvelItem.urls) }
            )
        },
        floatingActionButton = {
            if(marvelItem.urls.isNotEmpty()) {
                FloatingActionButton(onClick = { shareCharacter(context, marvelItem.title, marvelItem.urls.first()) }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
              BottomAppBar {
                  AppBarIcon(imageVector = Icons.Default.Menu, onClick = { /*TODO*/ }, contentDescription = "Menu")
                  Spacer(modifier = Modifier.weight(1f))
                  AppBarIcon(imageVector = Icons.Default.Favorite, onClick = { /*TODO*/ }, contentDescription = "Favorite")
              }      
        },
        content = content
    )
}

fun shareCharacter(context: Context, name: String, url: Url) {
    ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setSubject(name)
        .setText(url.destination)
        .intent
        .also(context::startActivity)
}

