package com.aiglepub.marvelcompose.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.aiglepub.marvelcompose.MarvelApp
import com.aiglepub.marvelcompose.data.entities.Character
import com.aiglepub.marvelcompose.data.repositories.CharactersRepository

@Composable
fun CharacterDetailScreen(id: Int) {

    var characterState by remember { mutableStateOf<Character?>(null) }

    LaunchedEffect(key1 = true) {
        characterState = CharactersRepository.findCharacter(id)
    }
    characterState?.let {character -> CharacterDetailScreenContent(character) }

}

@Composable
fun CharacterDetailScreenContent(character: Character) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item{
            Header(character)
        }

    }
}

@Composable
fun Header(character: Character) {
    Column(
        modifier = Modifier.fillMaxWidth()
    )  {
        Image(
            painter = rememberImagePainter(data = character),
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 32.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.description,
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
private fun CharacterDetailScreen_Content() {
    val character = Character(
        1,
        "Iron Man",
        "Soy como un Robot humano",
        ""
    )
    MarvelApp {
        CharacterDetailScreenContent(character = character)
    }
}