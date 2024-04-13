package com.aiglepub.marvelcompose.ui.screens.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aiglepub.marvelcompose.data.entities.Character
import com.aiglepub.marvelcompose.data.repositories.CharactersRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(onClick: (Character) -> Unit) {

    var charactersState by rememberSaveable { mutableStateOf(emptyList<Character>()) }

    // En la key le paso el argumento que si cambia se volveria a ejecutar
    // Si le pongo TRUE eso es un estado que no cambia y solo se ejecuta 1 vez
    LaunchedEffect(key1 = true) {
        charactersState = CharactersRepository.getCharacters()
    }

    CharactersScreenContent(
        characters = charactersState,
        onClick = { onClick(it) },)

}

@Composable
fun CharactersScreenContent(characters: List<Character>, onClick: (Character) -> Unit) {
    val finalList = characters.filter {
       it.thumbnail.isNotEmpty()
    }

    Scaffold(

    ) {paddingValues->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(180.dp),
            contentPadding = paddingValues
        ) {
            items(finalList) {character ->
                CharacterItem(
                    character = character,
                    modifier = Modifier.clickable { onClick(character) }
                )
            }
        }
    }

}

@Composable
fun CharacterItem(character: Character ,modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Card {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.thumbnail)
                    .crossfade(2000)
                    .build(),
                contentDescription = "Photo Chracter",
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = character.name,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 2,
            modifier = Modifier.padding(8.dp, 16.dp)
        )
    }
}
