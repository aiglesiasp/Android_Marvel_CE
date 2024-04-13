package com.aiglepub.marvelcompose.ui.screens.characters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import coil.annotation.ExperimentalCoilApi
import com.aiglepub.marvelcompose.data.entities.Character
import com.aiglepub.marvelcompose.data.repositories.CharactersRepository
import com.aiglepub.marvelcompose.ui.screens.commons.MarvelItemDetailScreen
import com.aiglepub.marvelcompose.ui.screens.commons.MarvelItemsListScreen

@Composable
fun CharactersScreen(onClick: (Character) -> Unit) {

    var charactersState by rememberSaveable { mutableStateOf(emptyList<Character>()) }

    // En la key le paso el argumento que si cambia se volveria a ejecutar
    // Si le pongo TRUE eso es un estado que no cambia y solo se ejecuta 1 vez
    LaunchedEffect(key1 = true) {
        charactersState = CharactersRepository.get()
    }

    MarvelItemsListScreen(
        items = charactersState,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@Composable
fun CharacterDetailScreen(characterId: Int, onUpClick: () -> Unit) {
    var charactersState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit) {
        charactersState = CharactersRepository.find(characterId)
    }
    charactersState?.let {
        MarvelItemDetailScreen(it, onUpClick)
    }
}