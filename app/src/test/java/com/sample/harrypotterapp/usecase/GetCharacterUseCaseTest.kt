package com.sample.harrypotterapp.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.harrypotterapp.data.repository.CharactersRepositoryImpl
import com.sample.harrypotterapp.domain.common.Resource
import com.sample.harrypotterapp.domain.model.CharacterModel
import com.sample.harrypotterapp.domain.usecase.GetCharactersUseCase
import com.sample.harrypotterapp.presentation.characters_list.state.CharacterListState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
class GetCharacterUseCaseTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)
    private val repository = mockk<CharactersRepositoryImpl>()
    private val getCharactersUseCase = GetCharactersUseCase(repository)

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
    fun verifyGetCharactersUseCase() = runTest {
        // Given
        val expectedState = CharacterListState(characters = listOf(character))

        coEvery { repository.getCharacters() } returns flowOf(
            Resource.Success(
                listOf(
                    character
                )
            )
        )

        // When
        val result = getCharactersUseCase.invoke()
        advanceUntilIdle()
        coVerify { repository.getCharacters() }
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
        private val character = CharacterModel(
            "Harry Potter",
            true,
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
            true,
            3
        )
    }
}
