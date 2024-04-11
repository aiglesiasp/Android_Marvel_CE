package com.aiglepub.marvelcompose.ui.screens.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    LazyVerticalGrid(columns = GridCells.Adaptive(180.dp)) {
        items(characters) {
            CharacterItem(character = it)
        }
    }
}

@Composable
fun CharacterItem(character: Character) {
    Card {
        Column(
            modifier = Modifier
        ) {

        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CharactersScreen_Preview() {
    CharactersScreen()
}