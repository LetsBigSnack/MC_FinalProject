package com.cc221019.finalproject.model.states

import com.cc221019.demo_two.ui.views.Screen
import com.cc221019.finalproject.data.models.entities.Player

data class LevelUpViewState(
    val selectedPlayer: Player? = null,
    val stats : Map<String, Int> = mutableMapOf(
        "Strength" to 1,
        "Stamina" to 1,
        "Dexterity" to 1,
        "Constitution" to 1,
        "Motivation" to 1
    )
    )