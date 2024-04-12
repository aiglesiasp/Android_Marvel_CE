package com.aiglepub.marvelcompose.data.repositories

import com.aiglepub.marvelcompose.data.entities.Character
import com.aiglepub.marvelcompose.data.network.api.ApiClient
import com.aiglepub.marvelcompose.data.network.entities.asString

object CharactersRepository {

    suspend fun getCharacters(): List<Character> {
        //Obtenemos los resultados de la llamada
        val result = ApiClient.charactersService.getCharacters(0, 100)

        //Convertimos los datos recibidos a los que queremos mandar
        return result.data.results.map {
          Character(id = it.id, name = it.name, description = it.description, thumbnail = it.thumbnail.asString())
        }
    }
}