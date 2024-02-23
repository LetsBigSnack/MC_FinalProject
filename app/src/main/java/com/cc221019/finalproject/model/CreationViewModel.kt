package com.cc221019.finalproject.model

import androidx.lifecycle.ViewModel
import com.cc221019.finalproject.data.DatabaseHandler
import com.cc221019.finalproject.data.models.entities.Player
import com.cc221019.finalproject.model.states.CreationViewState
import com.cc221019.finalproject.model.states.LoginViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class CreationViewModel (val db: DatabaseHandler) : ViewModel() {

    private val _creationViewState = MutableStateFlow(CreationViewState())
    val creationViewState: StateFlow<CreationViewState> = _creationViewState.asStateFlow()

    fun nextStep(){
        _creationViewState.update { it.copy(stepNumber = creationViewState.value.stepNumber+1) }
        println(creationViewState.value.stepNumber);
    }

    fun previousStep(){
        _creationViewState.update { it.copy(stepNumber = creationViewState.value.stepNumber-1) }
        println(creationViewState.value.stepNumber);
    }

    fun changeCharacterName(name : String){
        _creationViewState.update { it.copy(characterName = name) }
    }
    fun selectClass(selectedClass: String){
        _creationViewState.update { it.copy(characterClass = selectedClass) }
    }

    fun addStat(statName : String){
        if(creationViewState.value.statPoints >= 1){
            _creationViewState.update { currentState ->
                val updatedStats = currentState.stats.toMutableMap().apply {
                    this[statName] = (this[statName] ?: 0) + 1
                }
                currentState.copy(stats = updatedStats)
            }
            spendStatPoints();
        }
    }

    fun subStat(statName : String){
        if(creationViewState.value.stats[statName]!! > 1){
            _creationViewState.update { currentState ->
                val updatedStats = currentState.stats.toMutableMap().apply {
                    this[statName] = (this[statName] ?: 0) - 1
                }
                currentState.copy(stats = updatedStats)
            }
            refundStatPoints()
        }
    }

    fun refundStatPoints(){
        _creationViewState.update { it.copy(statPoints = creationViewState.value.statPoints+1) }
    }

    fun spendStatPoints(){
        _creationViewState.update { it.copy(statPoints = creationViewState.value.statPoints-1) }
    }

    fun createCharacter(){
        var createdPlayer = creationViewState.value.createdPlayer;
        createdPlayer.playerName = creationViewState.value.characterName;
        createdPlayer.playerClass = creationViewState.value.characterClass;
        createdPlayer.playerLevel = 1;
        createdPlayer.playerStrength = creationViewState.value.stats["Strength"]!!;
        createdPlayer.playerDexterity = creationViewState.value.stats["Dexterity"]!!;
        createdPlayer.playerStamina = creationViewState.value.stats["Stamina"]!!;
        createdPlayer.playerConstitution =creationViewState.value.stats["Constitution"]!!;
        createdPlayer.playerMotivation = creationViewState.value.stats["Motivation"]!!;
        createdPlayer.playerAttributePoints = creationViewState.value.statPoints;
        createdPlayer.lastPlayed = true;
        //TODO remove lastPlayed from all other Players

        db.insertPlayer(createdPlayer);
        println("CHAR CREATED");
    }
}