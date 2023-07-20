package com.example.quizdynamox.navigation

sealed class Screens(val route: String, val title: String) {
    object SplashScreen:Screens("splash_screen", "Splash")
    object InitialScreen : Screens("initial_screen", "Initial")
    object QuizScreen : Screens("quiz_screen", "Quiz")
    object ResultScreen : Screens("result_screen", "Result")
}