package com.cc221019.finalproject.model.states

import com.cc221019.finalproject.data.models.entities.Player

data class CharacterViewState(
    val characters: List<Player> = emptyList(),
    val editPlayer: Player? = null,
    val deletePlayer: Player? = null,
    val selectedPlayer: Player? = null,
    val openPlayerEditDialog: Boolean = false,
    val openPlayerDeleteDialog: Boolean = false,
    val openPlayerSelectDialog: Boolean = false,
)