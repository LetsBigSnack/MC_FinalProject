package com.cc221019.finalproject.model

import androidx.lifecycle.ViewModel
import com.cc221019.finalproject.data.DatabaseHandler
import com.cc221019.finalproject.model.states.LoginViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class LoginViewModel (val db: DatabaseHandler) : ViewModel() {

    private val _loginViewState = MutableStateFlow(LoginViewState())
    val loginViewState: StateFlow<LoginViewState> = _loginViewState.asStateFlow()

    fun getPlayers(){
        _loginViewState.update { it.copy(players = db.getPlayers()) }
    }

}