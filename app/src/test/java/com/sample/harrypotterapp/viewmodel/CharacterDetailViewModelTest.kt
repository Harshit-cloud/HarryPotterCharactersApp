package com.sample.harrypotterapp.viewmodel

import com.sample.harrypotterapp.domain.model.CharacterModel
import com.sample.harrypotterapp.presentation.character_detail.CharacterDetailViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CharacterDetailViewModelTest {

    private var viewModel: CharacterDetailViewModel = CharacterDetailViewModel()

    @Test
    fun selectCharacterTest() {
        // Given
        val expectedState = character

        // When
        viewModel.selectCharacter(character)

        // Then
        assertEquals(expectedState, viewModel.selectedCharacter)
    }

    @Test
    fun unselectCharacterTest() {
        // When
        viewModel.unselectCharacter()

        // Then
        assertEquals(null, viewModel.selectedCharacter)
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
