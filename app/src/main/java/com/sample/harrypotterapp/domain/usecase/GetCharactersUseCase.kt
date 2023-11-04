package com.sample.harrypotterapp.domain.usecase

import com.sample.harrypotterapp.domain.common.Resource
import com.sample.harrypotterapp.domain.model.CharacterModel
import com.sample.harrypotterapp.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharactersRepository) {

    operator fun invoke(): Flow<Resource<List<CharacterModel>>> {
        return repository.getCharacters()
    }
}