package com.cc221019.finalproject.model.states

import com.cc221019.finalproject.data.models.entities.Player

data class LoginViewState(
    val players: List<Player> = emptyList(),
    val selectedPlayer: Player = Player("","",1,1,1,1,1,1,1,true, 0),

    )