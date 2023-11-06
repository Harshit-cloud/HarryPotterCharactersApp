package com.sample.harrypotterapp.presentation.characters_list.state

import com.sample.harrypotterapp.domain.model.CharacterModel


data class CharacterListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val characters: List<CharacterModel> = emptyList()
)
