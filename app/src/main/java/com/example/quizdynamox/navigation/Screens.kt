package com.example.quizdynamox.navigation

import com.example.quizdynamox.INITIAL_ROUTE
import com.example.quizdynamox.QUIZ_ROUTE
import com.example.quizdynamox.END_GAME_ROUTE

sealed class Screens(val route: String) {
    object InitialScreen : Screens(INITIAL_ROUTE)
    object QuizScreen : Screens(QUIZ_ROUTE)
    object ResultScreen : Screens(END_GAME_ROUTE)
}