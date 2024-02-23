package com.cc221019.finalproject.model.states

import com.cc221019.demo_two.ui.views.Screen
import com.cc221019.finalproject.data.models.entities.Enemy
import com.cc221019.finalproject.data.models.entities.Player

data class MainViewState(
    val selectedPlayer: Player? = null,
    val selectedScreen: Screen = Screen.Character,
    val battleStarted : Boolean = false,
    val battleComplete: Boolean = false,
    val enemy: Enemy? = null,
    var playerText :String = "",
    val enemyText : String="",
    val battleCompleteText : String = "",
    val currentPlayerHealth : Int = 1,
    val currentEnemyHealth : Int = 1,
    )