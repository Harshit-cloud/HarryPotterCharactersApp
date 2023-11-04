package com.sample.harrypotterapp.presentation.character_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sample.harrypotterapp.domain.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor() : ViewModel() {
    var selectedCharacter by mutableStateOf<CharacterModel?>(null)
        private set

    fun selectCharacter(data: CharacterModel) {
        selectedCharacter = data
    }

    fun unselectCharacter() {
        selectedCharacter = null
    }
}