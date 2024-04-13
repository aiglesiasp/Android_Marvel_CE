package com.aiglepub.marvelcompose.data.repositories

import asCharacter
import com.aiglepub.marvelcompose.data.entities.Character
import com.aiglepub.marvelcompose.data.network.api.ApiClient

object CharactersRepository: Repository<Character>() {
/*
    suspend fun getCharacters(): List<Character> {
        //Obtenemos los resultados de la llamada
        val result = ApiClient.charactersService.getCharacters(0, 100)
        //Convertimos los datos recibidos a los que queremos mandar
        return result.data.results.map { it.asCharacter() }
    }

    suspend fun findCharacter(characterId: Int): Character {
        val result = ApiClient.charactersService.findCharacter(characterId = characterId)
        return result.data.results.first().asCharacter()
    }
*/
suspend fun get(): List<Character> = super.get {
    ApiClient
        .charactersService
        .getCharacters(0, 100)
        .data
        .results
        .map { it.asCharacter() }
}

    suspend fun find(id: Int): Character = super.find(
        id,
        findActionRemote = {
            ApiClient
                .charactersService
                .findCharacter(id)
                .data
                .results
                .first()
                .asCharacter()
        }
    )
}

