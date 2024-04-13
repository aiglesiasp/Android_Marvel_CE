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