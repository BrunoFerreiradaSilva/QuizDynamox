package com.example.quizdynamox.ui.screens.home

import android.app.Activity
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizdynamox.R
import com.example.quizdynamox.ui.components.ButtonComponent
import com.example.quizdynamox.ui.components.TextErrorComponent

@Composable
fun HomeScreen(onQuizScreen: (String) -> Unit) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val state by viewModel.uiState.collectAsState()
    val activity = LocalContext.current as Activity

    val nameUser = remember { mutableStateOf(TextFieldValue()) }

    BackHandler {
        activity.finish()
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        NameForGame(nameUser, state.fieldValid)

        state.showErrorName?.let {
            if (it){
                TextErrorComponent(
                    messageError = stringResource(id = R.string.error_insert_name)
                )
            }
        }


        ButtonComponent(labelText = "Start Quiz") {
            viewModel.validateName(name = nameUser.value.text) { invalid ->
                if (!invalid) {
                    onQuizScreen(nameUser.value.text)
                }
            }
        }

    }
}

@Composable
private fun NameForGame(
    nameUser: MutableState<TextFieldValue>,
    isError: Boolean
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
        isError = isError,
        maxLines = 1
    )
}
