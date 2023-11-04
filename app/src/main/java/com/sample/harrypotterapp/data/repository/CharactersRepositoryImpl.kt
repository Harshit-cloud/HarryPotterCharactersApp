package com.sample.harrypotterapp.data.repository

import com.sample.harrypotterapp.domain.common.Resource
import com.sample.harrypotterapp.domain.model.Characters
import com.sample.harrypotterapp.data.model.toCharacter
import com.sample.harrypotterapp.data.remote.CharactersApi
import com.sample.harrypotterapp.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class CharactersRepositoryImpl @Inject constructor(private val api: CharactersApi) :
    CharactersRepository {
    override fun getCharacters(): Flow<Resource<List<Characters>>> = flow {
        emit(Resource.Loading())

        try {
            val allCharacters = api.getCharacters().map { it.toCharacter() }
            emit(Resource.Success(allCharacters))

        } catch (exception: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server. Check your internet connection."
                )
            )
        } catch (exception: HttpException) {
            emit(
                Resource.Error(
                    message = exception.localizedMessage ?: "An unexpected error occurred"
                )
            )
        }
    }

}