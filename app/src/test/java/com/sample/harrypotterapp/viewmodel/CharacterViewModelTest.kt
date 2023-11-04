package com.sample.harrypotterapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.harrypotterapp.domain.common.Resource
import com.sample.harrypotterapp.domain.model.Characters
import com.sample.harrypotterapp.domain.usecase.GetCharactersUseCase
import com.sample.harrypotterapp.presentation.characters_list.CharacterListViewModel
import com.sample.harrypotterapp.presentation.characters_list.state.CharacterListState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CharacterViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var viewModel: CharacterListViewModel
    private val getCharactersUseCase = mockk<GetCharactersUseCase>()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CharacterListViewModel(getCharactersUseCase, testDispatcher)
    }

    @Test
    fun `getCharacters updates characters state`() = testScope.runTest {
        // Given
        val expectedState = CharacterListState(characters = listOf())

        coEvery { getCharactersUseCase.invoke() } returns flowOf(
            Resource.Success(
                listOf(
                    Characters(
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
                    ),
                )
            )
        )

        // When
        viewModel.getCharacters()

        // Then
        assertEquals(expectedState, viewModel.characters.value)
    }
}
