package com.cc221019.finalproject.model

import androidx.lifecycle.ViewModel
import com.cc221019.demo_two.ui.views.Screen
import com.cc221019.finalproject.data.DatabaseHandler
import com.cc221019.finalproject.data.models.entities.Enemy
import com.cc221019.finalproject.model.states.LoginViewState
import com.cc221019.finalproject.model.states.MainViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random


class MainViewModel (val db: DatabaseHandler) : ViewModel() {

    private val _mainViewState = MutableStateFlow(MainViewState())
    val mainViewState: StateFlow<MainViewState> = _mainViewState.asStateFlow()


    fun getPlayer(){
        _mainViewState.update { it.copy(selectedPlayer = db.getSelectedPlayer()) }
    }

    fun selectScreen(screen: Screen){
        _mainViewState.update { it.copy(selectedScreen = screen) }
    }

    fun startBattle() {
        _mainViewState.value.selectedPlayer!!.getNewHealth();
        _mainViewState.update { it.copy(enemy = Enemy("Monster")) }
        _mainViewState.update { it.copy(battleStarted = true) }
        _mainViewState.update { it.copy(currentEnemyHealth =  _mainViewState.value.enemy!!.entityCurrentHealth) }
        _mainViewState.update { it.copy(currentPlayerHealth =  _mainViewState.value.selectedPlayer!!.entityCurrentHealth) }
    }

    fun leaveBattle(){
        _mainViewState.update { it.copy(battleStarted = false) }
        _mainViewState.update { it.copy(battleComplete = false) }
    }

    fun useAbility(abilityNumber:Int){

        when(abilityNumber){
            1 -> {
                _mainViewState.update { it.copy(playerText = _mainViewState.value.selectedPlayer!!.abilityOne(_mainViewState.value.enemy!!)) };
                _mainViewState.update { it.copy(enemyText = _mainViewState.value.enemy!!.battle(_mainViewState.value.selectedPlayer!!)) };
            }
            2 -> {
                _mainViewState.update { it.copy(playerText = _mainViewState.value.selectedPlayer!!.abilityTwo(_mainViewState.value.enemy!!)) };
                _mainViewState.update { it.copy(enemyText = _mainViewState.value.enemy!!.battle(_mainViewState.value.selectedPlayer!!)) };
            }
            3 -> {
                _mainViewState.update { it.copy(playerText = _mainViewState.value.selectedPlayer!!.abilityThree(_mainViewState.value.enemy!!)) };
                _mainViewState.update { it.copy(enemyText = _mainViewState.value.enemy!!.battle(_mainViewState.value.selectedPlayer!!)) };
            }
            4 -> {
                _mainViewState.update { it.copy(playerText = _mainViewState.value.selectedPlayer!!.abilityFour(_mainViewState.value.enemy!!)) };
                _mainViewState.update { it.copy(enemyText = _mainViewState.value.enemy!!.battle(_mainViewState.value.selectedPlayer!!)) };
            }
        }

        _mainViewState.update { it.copy(currentEnemyHealth =  _mainViewState.value.enemy!!.entityCurrentHealth) }
        _mainViewState.update { it.copy(currentPlayerHealth =  _mainViewState.value.selectedPlayer!!.entityCurrentHealth) }


        if(!_mainViewState.value.enemy!!.isAlive && _mainViewState.value.selectedPlayer!!.isAlive){
            _mainViewState.update { it.copy(battleComplete = true)}
            val xpGain = Random.nextInt(1,5);
            _mainViewState.value.selectedPlayer!!.earnXP(xpGain)
            _mainViewState.update { it.copy(battleCompleteText = "Enemy defeated earned $xpGain XP")}
            db.updatePlayer(_mainViewState.value.selectedPlayer!!)
        }else if(!_mainViewState.value.selectedPlayer!!.isAlive){
            _mainViewState.update { it.copy(battleComplete = true)}
            _mainViewState.update { it.copy(battleCompleteText = "Player defeated")}
        }
    }


}