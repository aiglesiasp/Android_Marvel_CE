package com.aiglepub.marvelcompose.data.repositories

import asComic
import com.aiglepub.marvelcompose.data.entities.Comic
import com.aiglepub.marvelcompose.data.network.api.ApiClient
import toStringFormat

object ComicsRepository : Repository<Comic>() {

    suspend fun get(format: Comic.Format? = null): List<Comic> = super.get {
        ApiClient
            .comicsService
            .getComics(0, 100, format?.toStringFormat())
            .data
            .results
            .map { it.asComic() }
    }

    suspend fun find(id: Int): Comic = super.find(
        id = id,
        findActionRemote = {
            ApiClient
                .comicsService
                .findComic(id)
                .data
                .results
                .first()
                .asComic()
        }
    )
}