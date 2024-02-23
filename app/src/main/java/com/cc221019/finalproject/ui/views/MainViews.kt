package com.cc221019.demo_two.ui.views

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cc221019.finalproject.CreationActivity
import com.cc221019.finalproject.LevelUpActivity
import com.cc221019.finalproject.LoginActivity
import com.cc221019.finalproject.MainActivity
import com.cc221019.finalproject.R
import com.cc221019.finalproject.data.models.entities.Player
import com.cc221019.finalproject.model.MainViewModel

sealed class Screen(val route: String){
    object Character: Screen("Character")
    object Dungeon: Screen("Dungeon")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(mainViewModel: MainViewModel) {

    val state = mainViewModel.mainViewState.collectAsState()
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {BottomNavigationBar(navController, state.value.selectedScreen)}
    ) {
        NavHost(
            navController = navController,
            modifier = Modifier.padding(it),
            startDestination = Screen.Character.route
        ){
            composable(Screen.Character.route){
                mainViewModel.getPlayer();
                mainViewModel.selectScreen(Screen.Character);
                displayCharacterSheet(mainViewModel)
            }
            composable(Screen.Dungeon.route){
                mainViewModel.getPlayer();
                mainViewModel.selectScreen(Screen.Dungeon);
                displayBattleScreen(mainViewModel);
                //mainScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, selectedScreen: Screen){
    BottomNavigation (
        backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        NavigationBarItem(
            selected = (selectedScreen == Screen.Character),
            onClick = { navController.navigate(Screen.Character.route) },
            icon = { Icon(painter = painterResource(id = R.drawable.cowled), contentDescription = "Character Screen") })

        NavigationBarItem(
            selected = (selectedScreen == Screen.Dungeon),
            onClick = { navController.navigate(Screen.Dungeon.route) },
            icon = { Icon( painter = painterResource(id = R.drawable.battle_gear), contentDescription = "Dungeon Screen") })
    }
}

@Composable
fun displayCharacterSheet(mainViewModel: MainViewModel){

    val state = mainViewModel.mainViewState.collectAsState()
    val player = state.value.selectedPlayer;
    val context = LocalContext.current
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 20.dp, start = 20.dp)
    ) {
        if (player != null) {

            displayPlayerInfo(player);

            Spacer(modifier = Modifier.padding(15.dp))

            displayPlayerStats(player);

            Spacer(modifier = Modifier.padding(15.dp))

            displayPlayerAbilities(player);

        }

        Button(
            onClick = {
                val intent = Intent(context, LoginActivity::class.java);
                context.startActivity(intent);
            },
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Logout", fontSize = 25.sp)
        }

    }
}

@Composable
fun displayPlayerInfo(player: Player) {

    val context = LocalContext.current

    Text(
        text = player.playerName,
        fontSize = 30.sp,
        style = TextStyle(fontFamily = FontFamily.Monospace)
    )
    Text(
        text = "Class: "+player.playerClass,
        fontSize = 20.sp,
    )

    Text(
        text = "Level: " + player.playerLevel,
        fontSize = 20.sp,
    )

    Text(
        text = "Max HP: " + player.entityMaxHealth,
        fontSize = 20.sp,
    )
    //player.playerCurrentXP >= player.playerXPToNextLevel
    if(player.playerCurrentXP >= player.playerXPToNextLevel){
        Button(
            onClick = {
                val intent = Intent(context, LevelUpActivity::class.java); context.startActivity(intent);
            }
        ) {
            Text(text = "Level UP!", fontSize = 15.sp)
        }
    }else{
        Text(
            text = "XP: " + player.playerCurrentXP + "/" + player.playerXPToNextLevel,
            fontSize = 20.sp,
        )
    }
}

@Composable
fun displayPlayerStats(player: Player){
    Text(
        text = "Stats: ",
        fontSize = 30.sp,
    )

    Text(
        text = "Strength: " + player.playerStrength,
        fontSize = 20.sp,
    )
    Text(
        text = "Stamina: " + player.playerStamina,
        fontSize = 20.sp,
    )
    Text(
        text = "Dexterity: "  + player.playerDexterity,
        fontSize = 20.sp,
    )
    Text(
        text = "Constitution: "  + player.playerConstitution,
        fontSize = 20.sp,
    )
    Text(
        text = "Motivation: "  + player.playerMotivation,
        fontSize = 20.sp,
    )

}

@Composable
fun displayPlayerAbilities(player: Player){
    Text(
        text = "Abilities: ",
        fontSize = 30.sp,
    )

    Text(
        text = player.abilityOneName+ ": " + player.abilityOneDescription,
        fontSize = 20.sp,
    )
    Text(
        text = player.abilityTwoName+ ": " + player.abilityTwoDescription,
        fontSize = 20.sp,
    )
    Text(
        text = player.abilityThreeName+ ": " + player.abilityThreeDescription,
        fontSize = 20.sp,
    )
    Text(
        text = player.abilityFourName+ ": " + player.abilityFourDescription,
        fontSize = 20.sp,
    )

}


@Composable
fun displayBattleScreen(mainViewModel: MainViewModel) {

    val state = mainViewModel.mainViewState.collectAsState()

    if(state.value.battleStarted){
        displayBattleContent(mainViewModel);
    }else{
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,) {
            Button(
                onClick = {
                    mainViewModel.startBattle();
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Start Battle", fontSize = 20.sp)
            }

        }
    }
}

@Composable
fun displayBattleContent(mainViewModel: MainViewModel){

    val state = mainViewModel.mainViewState.collectAsState()
    val enemy = state.value.enemy;
    val player = state.value.selectedPlayer;

    val enemyText =  state.value.enemyText;
    val playerText = state.value.playerText;

    val enemyCurrentHealth = state.value.currentEnemyHealth;
    val playerCurrentHealth = state.value.currentPlayerHealth;
    val battleCompleteText = state.value.battleCompleteText;

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {

        // Enemy Section (Aligned Right)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.End, // Aligns content to the right
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth(0.5f)) {
                Box(modifier = Modifier
                    .size(100.dp, 100.dp)
                    .background(Color.Gray))
                Text(text = "${enemy!!.entityName}", modifier = Modifier.padding(top = 4.dp))
                Row( verticalAlignment = Alignment.CenterVertically){
                    Text(text = "$enemyCurrentHealth/${enemy!!.entityMaxHealth}", modifier = Modifier.padding(4.dp))
                    LinearProgressIndicator(progress = (enemyCurrentHealth.toFloat()/enemy!!.entityMaxHealth.toFloat()).toFloat(), modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .height(10.dp), color = Color.Red)
                }
            }
        }

        // Player Section (Aligned Left)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Start, // Aligns content to the left
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth(0.5f)) {

                Box(modifier = Modifier
                    .size(100.dp, 100.dp)
                    .background(Color.Blue))
                Text(text = "${player!!.entityName}", modifier = Modifier.padding(top = 4.dp))
                Row( verticalAlignment = Alignment.CenterVertically){
                    Text(text = "$playerCurrentHealth/${player!!.entityMaxHealth}", modifier = Modifier.padding(4.dp))
                    LinearProgressIndicator(progress = (playerCurrentHealth.toFloat()/player!!.entityMaxHealth.toFloat()).toFloat(), modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .height(10.dp), color = Color.Green)
                }

            }
        }


        Text(text = "$enemyText", fontSize = 20.sp)
        Text(text = "$playerText", fontSize = 20.sp)


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            if(!state.value.battleComplete){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { mainViewModel.useAbility(1) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp)
                    ) {
                        Text(text = player!!.abilityOneName, fontSize = 20.sp)
                    }
                    Button(
                        onClick = {  mainViewModel.useAbility(2) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                    ) {
                        Text(text = player!!.abilityTwoName, fontSize = 20.sp)
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {  mainViewModel.useAbility(3) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp)
                    ) {
                        Text(text = player!!.abilityThreeName, fontSize = 20.sp)
                    }
                    Button(
                        onClick = {  mainViewModel.useAbility(4) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                    ) {
                        Text(text = player!!.abilityFourName, fontSize = 20.sp)
                    }
                }
            }else{
                Text(text = "$battleCompleteText", fontSize = 20.sp)
            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Button(
                    onClick = {
                        mainViewModel.leaveBattle();
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Run away", fontSize = 20.sp)
                }
            }

        }
    }
}