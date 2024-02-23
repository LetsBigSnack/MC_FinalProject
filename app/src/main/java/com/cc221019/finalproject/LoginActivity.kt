package com.cc221019.finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cc221019.finalproject.data.DatabaseHandler
import com.cc221019.finalproject.model.LoginViewModel
import com.cc221019.finalproject.ui.theme.FinalprojectTheme
import com.cc221019.finalproject.ui.views.LoginView

class LoginActivity : ComponentActivity() {

    private val db = DatabaseHandler(this)
    private val loginViewModel = LoginViewModel(db)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalprojectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    loginViewModel.getPlayers();
                    LoginView(loginViewModel, this);
                }
            }
        }
    }
}