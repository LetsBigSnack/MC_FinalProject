package com.cc221019.finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.cc221019.demo_two.ui.views.MainView
import com.cc221019.finalproject.data.DatabaseHandler
import com.cc221019.finalproject.model.MainViewModel
import com.cc221019.finalproject.ui.theme.FinalprojectTheme

class MainActivity : ComponentActivity() {


    private val db = DatabaseHandler(this)
    private val mainViewModel = MainViewModel(db)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalprojectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    mainViewModel.getPlayer();
                    MainView(mainViewModel)
                }
            }
        }
    }
}