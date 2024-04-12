package com.aiglepub.marvelcompose.data.repositories

import com.aiglepub.marvelcompose.data.entities.Character
import com.aiglepub.marvelcompose.data.entities.Reference
import com.aiglepub.marvelcompose.data.entities.Character as NetworkCharacter
import com.aiglepub.marvelcompose.data.network.api.ApiClient
import com.aiglepub.marvelcompose.data.network.entities.asString

object CharactersRepository {

    suspend fun getCharacters(): List<Character> {
        //Obtenemos los resultados de la llamada
        val result = ApiClient.charactersService.getCharacters(0, 100)

        //Convertimos los datos recibidos a los que queremos mandar
        return result.data.results.map { apiCharacter ->
            Character(
              id = apiCharacter.id,
              name = apiCharacter.name,
              description = apiCharacter.description,
              thumbnail = apiCharacter.thumbnail.asString(),
              //comics = apiCharacter.comics.items.map { Reference(it.name) },
              //events = apiCharacter.events.items.map { Reference(it.name) } ,
              //stories = apiCharacter.stories.items.map { Reference(it.name) },
              //series = apiCharacter.series.items.map { Reference(it.name) }
          )
        }
    }

    suspend fun findCharacter(characterId: Int): Character {
        val result = ApiClient.charactersService.findCharacter(characterId = characterId)
        return result.data.results.map { apiCharacter ->
            Character(
                id = apiCharacter.id,
                name = apiCharacter.name,
                description = apiCharacter.description,
                thumbnail = apiCharacter.thumbnail.asString(),
                //comics = apiCharacter.comics.items.map { Reference(it.name) },
                //events = apiCharacter.events.items.map { Reference(it.name) } ,
                //stories = apiCharacter.stories.items.map { Reference(it.name) },
                //series = apiCharacter.series.items.map { Reference(it.name) }
            )
        }.first()
    }
}

//EXTENSION PARA CONVERTIR UN NETWORKCHARACTER en un CHARACTER
fun NetworkCharacter.asCharacter(): Character {
    //val comics = comics.map { Reference(it.name) }

    return Character(
        id = id,
        name = name,
        description = description,
        thumbnail = thumbnail
    )
}