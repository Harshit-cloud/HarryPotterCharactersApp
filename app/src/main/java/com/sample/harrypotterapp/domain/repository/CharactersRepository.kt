package com.sample.harrypotterapp.domain.repository

import com.sample.harrypotterapp.domain.common.Resource
import com.sample.harrypotterapp.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters(): Flow<Resource<List<CharacterModel>>>
}