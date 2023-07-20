package com.example.quizdynamox.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.quizdynamox.navigation.Screens
import com.example.quizdynamox.ui.components.ButtonComponent

@Composable
fun HomeScreen(navHostController: NavHostController) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val nameUser = remember { mutableStateOf(TextFieldValue()) }
    val isLoading = remember {
        mutableStateOf(false)
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nameUser.value,
            onValueChange = { nameUser.value = it },
            label = { Text(text = "Nome ou Apelido") },
            shape = ShapeDefaults.Medium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            maxLines = 1
        )
        ButtonComponent(labelText = "Start Quiz", isLoading) {
            //homeViewModel.insertPlayer(nameUser.value.text)
            navHostController.navigate("${Screens.QuizScreen.route}/${nameUser.value.text}")
        }
    }

}