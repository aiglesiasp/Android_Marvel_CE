package com.aiglepub.marvelcompose.data.network.entities

import com.aiglepub.marvelcompose.data.entities.Character
import com.aiglepub.marvelcompose.data.entities.Reference
import java.util.Date

data class ApiCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ApiThumbnail,
    val comics: ApiReferenceList,
    val events: ApiReferenceList,
    val series: ApiReferenceList,
    val stories: ApiReferenceList,
    val urls: List<ApiUrl>,
    val modified: Date,
    val resourceURI: String
)

//EXTENSION PARA CONVERTIR UN NETWORKCHARACTER en un CHARACTER
fun ApiCharacter.asCharacter(): Character {
    val comics = comics.items.map { Reference(it.name) }
    val events = events.items.map { Reference(it.name) }
    val stories = stories.items.map { Reference(it.name) }
    val series = series.items.map { Reference(it.name) }

    return Character(
        id = id,
        name = name,
        description = description,
        thumbnail = thumbnail.asString(),
        comics = comics,
        events = events,
        stories = stories,
        series = series
    )
}