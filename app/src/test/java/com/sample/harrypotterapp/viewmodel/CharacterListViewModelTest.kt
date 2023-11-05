package com.sample.harrypotterapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.harrypotterapp.domain.common.Resource
import com.sample.harrypotterapp.domain.model.CharacterModel
import com.sample.harrypotterapp.domain.usecase.GetCharactersUseCase
import com.sample.harrypotterapp.presentation.characters_list.CharacterListViewModel
import com.sample.harrypotterapp.presentation.characters_list.state.CharacterListState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
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
class CharacterListViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: CharacterListViewModel
    private val getCharactersUseCase = mockk<GetCharactersUseCase>()

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
    fun getCharactersSuccessTest() = runTest(testDispatcher) {
        // Given
        val expectedState = CharacterListState(characters = listOf(character))

        coEvery { getCharactersUseCase.invoke() } returns flowOf(
            Resource.Success(
                listOf(
                    character
                )
            )
        )

        // When
        viewModel = CharacterListViewModel(getCharactersUseCase, testDispatcher)
        advanceUntilIdle()
        coVerify { getCharactersUseCase.invoke() }
        assertNotNull(viewModel.characters.value.characters)

        // Then
        assertEquals(expectedState.characters, viewModel.characters.value.characters)
    }

    @Test
    fun getCharacterErrorTest() = runTest(testDispatcher) {
        // Given
        val expectedState =
            CharacterListState(error = "An unexpected error occurred", characters = listOf())

        coEvery { getCharactersUseCase.invoke() } returns flowOf(
            Resource.Error("An unexpected error occurred")
        )

        // When
        viewModel = CharacterListViewModel(getCharactersUseCase, testDispatcher)
        advanceUntilIdle()
        coVerify { getCharactersUseCase.invoke() }
        // Then
        assertEquals(expectedState.error, viewModel.characters.value.error)
    }


    @Test
    fun setSearchStringTest() {
        // Given
        viewModel = CharacterListViewModel(getCharactersUseCase, testDispatcher)
        viewModel.setSearchString("Draco")

        // Then
        assertEquals("Draco", viewModel.searchString.value)
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
