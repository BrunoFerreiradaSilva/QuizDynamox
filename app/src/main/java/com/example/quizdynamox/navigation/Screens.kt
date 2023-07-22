package com.example.quizdynamox.navigation

sealed class Screens(val route: String) {
    object InitialScreen : Screens("initial_screen")
    object QuizScreen : Screens("quiz_screen")
    object ResultScreen : Screens("result_screen")
}