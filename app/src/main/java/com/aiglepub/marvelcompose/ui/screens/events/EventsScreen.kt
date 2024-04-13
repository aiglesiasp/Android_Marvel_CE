package com.aiglepub.marvelcompose.ui.screens.events

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import coil.annotation.ExperimentalCoilApi
import com.aiglepub.marvelcompose.data.entities.Event
import com.aiglepub.marvelcompose.data.repositories.EventsRepository
import com.aiglepub.marvelcompose.ui.screens.commons.MarvelItemDetailScreen
import com.aiglepub.marvelcompose.ui.screens.commons.MarvelItemsListScreen

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun EventsScreen(onClick: (Event) -> Unit) {
    var eventsState by remember() { mutableStateOf(emptyList<Event>()) }
    LaunchedEffect(Unit) {
        eventsState = EventsRepository.get()
    }
    MarvelItemsListScreen(
        items = eventsState,
        onClick = onClick
    )
}

@ExperimentalCoilApi
@Composable
fun EventDetailScreen(eventId: Int, onUpClick: () -> Unit) {
    var eventState by remember { mutableStateOf<Event?>(null) }
    LaunchedEffect(Unit) {
        eventState = EventsRepository.find(eventId)
    }
    eventState?.let {
        MarvelItemDetailScreen(it, onUpClick)
    }
}