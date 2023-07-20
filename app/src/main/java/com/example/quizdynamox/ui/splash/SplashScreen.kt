package com.example.quizdynamox.ui.splash

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navHostController: NavHostController) {
    val splashViewModel = hiltViewModel<SplashViewModel>()

    splashViewModel.viewModelScope.launch {
        splashViewModel.uiState.collect {
            navHostController.navigate(it.rote)
        }
    }

}