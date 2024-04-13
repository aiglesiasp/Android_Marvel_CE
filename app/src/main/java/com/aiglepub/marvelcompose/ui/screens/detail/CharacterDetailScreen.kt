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
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.aiglepub.marvelcompose.MarvelApp
import com.aiglepub.marvelcompose.data.entities.Character
import com.aiglepub.marvelcompose.data.entities.Reference
import com.aiglepub.marvelcompose.data.repositories.CharactersRepository


@Composable
fun CharacterDetailScreen(id: Int, onUpClick: () -> Unit) {

    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(key1 = true) {
        characterState = CharactersRepository.findCharacter(id)
    }

    characterState?.let { character ->
        CharacterDetailScreenContent(character, onUpClick = { onUpClick() } )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreenContent(character: Character, onUpClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = character.name) },
                navigationIcon = {
                    IconButton(onClick = { onUpClick() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More Actions"
                        )
                        DropdownMenu(expanded = true, onDismissRequest = { /*TODO*/ }) {
                            character.urls.forEach {
                                DropdownMenuItem(
                                    text = { ListItem(headlineContent = { Text(text = it.type) }) },
                                    onClick = { /*TODO*/ }
                                )
                            }
                        }
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            item{
                Header(character)
            }
            section(Icons.Default.Collections, "SERIES", character.series)
            section(Icons.Default.Event, "EVENTS", character.events)
            section(Icons.Default.Book, "COMICS", character.comics)
            section(Icons.Default.Bookmark, "STORIES", character.stories)
        }
    }

}

@Composable
fun Header(character: Character) {
    Column(
        modifier = Modifier.fillMaxWidth()
    )  {
        Image(
            painter = rememberImagePainter(data = character.thumbnail),
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

        if(character.description.isNotEmpty()) {
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
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}


fun LazyListScope.section(icon: ImageVector, name: String, items: List<Reference>) {
    if (items.isEmpty()) return

    item {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp),
            color = Color.DarkGray
        )
    }

    items(items) {
        ListItem(
            headlineContent = { Text(text = it.name) },
            leadingContent = { Icon(imageVector = icon, contentDescription = null)}
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CharacterDetailScreen_Content() {
    val character = Character(
        1,
        "Iron Man",
        "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.",
        "",
        listOf(Reference("Comic1"), Reference("Comic2")),
        listOf(Reference("Events1"), Reference("Events2")),
        listOf(Reference("Stories1"), Reference("Stories2")),
        listOf(Reference("Series1"), Reference("Series2")),
        emptyList()
    )
    MarvelApp {
        CharacterDetailScreenContent(character = character, onUpClick = {})
    }
}