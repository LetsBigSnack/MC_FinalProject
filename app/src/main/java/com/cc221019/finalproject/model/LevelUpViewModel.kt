package com.cc221019.finalproject.model

import androidx.lifecycle.ViewModel
import com.cc221019.demo_two.ui.views.Screen
import com.cc221019.finalproject.data.DatabaseHandler
import com.cc221019.finalproject.model.states.LevelUpViewState
import com.cc221019.finalproject.model.states.LoginViewState
import com.cc221019.finalproject.model.states.MainViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class LevelUpViewModel (val db: DatabaseHandler) : ViewModel() {

    private val _levelUpViewState = MutableStateFlow(LevelUpViewState())
    val levelUpViewState: StateFlow<LevelUpViewState> = _levelUpViewState.asStateFlow()

    fun getPlayer() {
        _levelUpViewState.update { it.copy(selectedPlayer = db.getSelectedPlayer()) }
        getPlayerStats();
    }

    fun getPlayerStats(){

        val stats : Map<String, Int> = mutableMapOf(
            "Strength" to _levelUpViewState.value.selectedPlayer!!.getStats("Strength"),
            "Stamina" to _levelUpViewState.value.selectedPlayer!!.getStats("Stamina"),
            "Dexterity" to _levelUpViewState.value.selectedPlayer!!.getStats("Dexterity"),
            "Constitution" to _levelUpViewState.value.selectedPlayer!!.getStats("Constitution"),
            "Motivation" to _levelUpViewState.value.selectedPlayer!!.getStats("Motivation")
        )

        _levelUpViewState.update {it.copy(stats = stats)}
    }
    fun levelUp() {
        _levelUpViewState.value.selectedPlayer!!.levelUp();
    }

    fun addStat(statName : String){
        _levelUpViewState.value.selectedPlayer!!.addStat(statName);
        getPlayerStats();
    }

    fun subStat(statName: String){
        _levelUpViewState.value.selectedPlayer!!.subStat(statName);
        getPlayerStats();
    }

    fun saveStats(){
        _levelUpViewState.value.selectedPlayer!!.saveStats();
        db.updatePlayer(_levelUpViewState.value.selectedPlayer!!);

    }

}