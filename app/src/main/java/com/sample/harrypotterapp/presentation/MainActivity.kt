package com.sample.harrypotterapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.harrypotterapp.presentation.characters_list.CharacterListScreen
import com.sample.harrypotterapp.presentation.character_detail.CharacterDetailScreen
import com.sample.harrypotterapp.presentation.character_detail.CharacterDetailViewModel
import com.sample.harrypotterapp.presentation.ui.theme.JokesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost()

                }
            }
        }
    }
}

@Composable
fun NavHost(
    navController: NavHostController = rememberNavController(),
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CharacterListScreen.route,
    ) {
        composable(
            route = Screen.CharacterListScreen.route
        ) {
            CharacterListScreen(navController) { viewModel.selectCharacter(it) }
        }
        composable(
            route = Screen.CharacterDetailScreen.route
        ) {
            CharacterDetailScreen(viewModel = viewModel, navController = navController)
        }
    }

    viewModel.selectedCharacter?.let {
        BackHandler { viewModel.unselectCharacter() }
    }
}