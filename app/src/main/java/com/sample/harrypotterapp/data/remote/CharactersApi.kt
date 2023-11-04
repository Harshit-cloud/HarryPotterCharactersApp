package com.sample.harrypotterapp.data.remote

import com.sample.harrypotterapp.data.model.CharactersDto
import retrofit2.http.GET


interface CharactersApi {

    @GET("characters")
    suspend fun getCharacters(): List<CharactersDto>

}
