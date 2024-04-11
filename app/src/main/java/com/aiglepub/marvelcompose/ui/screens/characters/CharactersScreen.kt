package com.aiglepub.marvelcompose.ui.screens.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aiglepub.marvelcompose.MarvelApp
import com.aiglepub.marvelcompose.data.entities.Character

@Composable
fun CharactersScreen() {
    val charactersList = (1..10).map {
        Character(
            id = it,
            name = "Name $it",
            description = "Description $it",
            thumbnail = "https://via.placeholder.com/150x225/FFFF00/000000?text=name$it"
        )
    }
    CharactersScreenContent(characters = charactersList)
}

@Composable
fun CharactersScreenContent(characters: List<Character>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(characters) {
            CharacterItem(character = it)
        }
    }
}

@Composable
fun CharacterItem(character: Character) {

    Column(
        modifier = Modifier.padding(8.dp)
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

@Preview(showSystemUi = true)
@Composable
private fun CharactersScreen_Preview() {
    MarvelApp {
        CharactersScreen()
    }
}