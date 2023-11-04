package com.sample.harrypotterapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.harrypotterapp.data.model.CharactersDto
import com.sample.harrypotterapp.data.model.Wand
import com.sample.harrypotterapp.data.model.toCharacter
import com.sample.harrypotterapp.data.remote.CharactersApi
import com.sample.harrypotterapp.data.repository.CharactersRepositoryImpl
import com.sample.harrypotterapp.domain.common.Resource
import com.sample.harrypotterapp.presentation.characters_list.state.CharacterListState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CharacterRepositoryTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)
    private val charactersApi: CharactersApi = mockk()
    private val charactersRepository = CharactersRepositoryImpl(charactersApi)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // 2
        Dispatchers.resetMain()
    }

    @Test
    fun verifyCharactersRepository() = runTest {
        // Given
        val expectedState = CharacterListState(characters = listOf(characterDto.toCharacter()))

        coEvery { charactersApi.getCharacters() } returns listOf(characterDto)

        // When
        val result = charactersRepository.getCharacters()
        advanceUntilIdle()
        // Then
        result.onEach {
            when (it) {
                is Resource.Success -> {
                    assertEquals(expectedState.characters, it.data)
                }
                else -> {

                }
            }
        }.launchIn(testScope)
    }

    companion object {
        private val characterDto = CharactersDto(
            "Harry Potter",
            true,
            emptyList(),
            emptyList(),
            "djhds",
            "hdfhdf",
            "dsnd",
            "male",
            "hjds",
            true,
            false,
            "hdhd",
            "dhhd",
            "",
            "hdks",
            "dhdhd",
            "",
            Wand("hjj",0.0,"vhj"),
            true,
            3
        )
    }
}
