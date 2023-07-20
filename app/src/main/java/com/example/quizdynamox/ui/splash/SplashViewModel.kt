package com.example.quizdynamox.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizdynamox.data.repository.player.PlayerRepository
import com.example.quizdynamox.helpers.DataState
import com.example.quizdynamox.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SplashDataUi(
    val rote:String = Screens.QuizScreen.route
)

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: PlayerRepository): ViewModel(){

    private val _uiState: MutableStateFlow<SplashDataUi> =
        MutableStateFlow(SplashDataUi())

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllPlayers().collect{state ->
                when(state){
                    is DataState.Data -> {
                        state.data?.completeQuiz?.let {
                            if (it){
                                _uiState.value = SplashDataUi("initial_screen")
                            }
                        }?: run{
                            _uiState.value = SplashDataUi("initial_screen")
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}