package com.sample.harrypotterapp.data.model

import com.sample.harrypotterapp.domain.model.Characters


data class CharactersDto(
    val actor: String,
    val alive: Boolean,
    val alternate_actors: List<Any>,
    val alternate_names: List<String>,
    val ancestry: String,
    val dateOfBirth: String? = null,
    val eyeColour: String? = null,
    val gender: String,
    val hairColour: String? = null,
    val hogwartsStaff: Boolean? = null,
    val hogwartsStudent: Boolean? = null,
    val house: String? = null,
    val id: String? = null,
    val image: String? = null,
    val name: String,
    val patronus: String? = null,
    val species: String? = null,
    val wand: Wand,
    val wizard: Boolean? = null,
    val yearOfBirth: Int? = null
)

fun CharactersDto.toCharacter(): Characters {
    return Characters(
        actor,
        alive,
        ancestry,
        dateOfBirth,
        eyeColour,
        gender,
        hairColour,
        hogwartsStaff,
        hogwartsStudent,
        house,
        id,
        image,
        name,
        patronus,
        species,
        wizard,
        yearOfBirth
    )
}