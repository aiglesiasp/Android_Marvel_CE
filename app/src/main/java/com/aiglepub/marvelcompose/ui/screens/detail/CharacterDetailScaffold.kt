package com.aiglepub.marvelcompose.ui.screens.detail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.aiglepub.marvelcompose.data.entities.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScaffold(
    character: Character,
    onUpClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = character.name) },
                navigationIcon = {
                    IconButton(onClick = { onUpClick() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = { AppBarOverflowMenu(character.urls) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay,
        content = content
    )
}