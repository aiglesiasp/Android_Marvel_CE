package com.aiglepub.marvelcompose.ui.screens.comics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import coil.annotation.ExperimentalCoilApi
import com.aiglepub.marvelcompose.data.entities.Comic
import com.aiglepub.marvelcompose.data.repositories.ComicsRepository
import com.aiglepub.marvelcompose.ui.screens.commons.MarvelItemsListScreen
import com.aiglepub.marvelcompose.ui.screens.commons.MarvelItemDetailScreen

@Composable
fun ComicsScreen(onClick: (Comic) -> Unit) {
    var comicsState by remember() { mutableStateOf(emptyList<Comic>()) }
    LaunchedEffect(Unit) {
        comicsState = ComicsRepository.get()
    }
    MarvelItemsListScreen(
        items = comicsState,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@Composable
fun ComicDetailScreen(comicId: Int, onUpClick: () -> Unit) {
    var comicState by remember { mutableStateOf<Comic?>(null) }
    LaunchedEffect(Unit) {
        comicState = ComicsRepository.find(comicId)
    }
    comicState?.let {
        MarvelItemDetailScreen(it, onUpClick)
    }
}