package com.cc221019.finalproject.model.states

import com.cc221019.finalproject.data.models.entities.Player

data class CreationViewState(
    val stepNumber : Int = 1,
    val characterName: String = "",
    val characterClass: String = "",
    val createdPlayer: Player = Player("","",1,1,1,1,1,1,1,true),
    val statPoints : Int = 10,
    val stats : Map<String, Int> = mutableMapOf(
        "Strength" to 1,
        "Stamina" to 1,
        "Dexterity" to 1,
        "Constitution" to 1,
        "Motivation" to 1
    )
    )