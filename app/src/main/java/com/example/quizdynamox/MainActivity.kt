package com.example.quizdynamox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizdynamox.navigation.Screens
import com.example.quizdynamox.ui.screens.endGame.EndGameScreen
import com.example.quizdynamox.ui.screens.home.HomeScreen
import com.example.quizdynamox.ui.screens.quiz.QuizScreen
import com.example.quizdynamox.ui.theme.QuizDynamoxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            QuizDynamoxTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(navHostController = navHostController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screens.InitialScreen.route) {
        composable(
            route = Screens.InitialScreen.route
        ) {
            HomeScreen(onQuizScreen = {
                navHostController.navigate("${Screens.QuizScreen.route}/$it")
            })
        }
        composable(
            route = "${Screens.QuizScreen.route}/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { navBack ->
            val name = navBack.arguments?.getString("name")
            QuizScreen(onEndGameScreen = {
                navHostController.navigate(
                    "${Screens.ResultScreen.route}/${name}/$it"
                )
            })
        }
        composable(
            route = "${Screens.ResultScreen.route}/{nameUser}/{scoreGame}",
            arguments = listOf(navArgument("nameUser") { type = NavType.StringType },
                navArgument("scoreGame") { type = NavType.IntType }
            )
        ) { navBack ->
            EndGameScreen(onHomeScreen = {
                navHostController.navigate(Screens.InitialScreen.route)
            })
        }
    }
}