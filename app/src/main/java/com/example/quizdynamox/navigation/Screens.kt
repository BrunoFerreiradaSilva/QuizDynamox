package com.example.quizdynamox.navigation

sealed class Screens(val route: String, val title: String) {
    object InitialScreen : Screens("initial_screen", "Initial")
    object QuizScreen : Screens("quiz_screen", "Quiz")
    object ResultScreen : Screens("result_screen", "Result")
}