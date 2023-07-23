package com.example.quizdynamox.ui.screens.quizStart

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizdynamox.R
import com.example.quizdynamox.ui.components.ButtonComponent

@Composable
fun HomeScreen(navigateToQuizScreen: (String) -> Unit) {
    val viewModel = hiltViewModel<StartQuizViewModel>()
    val state by viewModel.uiState.collectAsState()

    val nameUser = remember { mutableStateOf(TextFieldValue()) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.padding(top = 10.dp),
            painter = painterResource(id = R.drawable.speech_bubble),
            contentDescription = stringResource(id = R.string.content_image_logo),

            )

        Text(
            text = stringResource(id = R.string.apresentation_app),
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold
        )


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
            value = nameUser.value,
            onValueChange = { nameUser.value = it },
            label = {
                Text(
                    text = stringResource(id = R.string.name_hint),
                    color = MaterialTheme.colorScheme.onTertiary
                )
            },
            shape = ShapeDefaults.Medium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colorScheme.onTertiary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            isError = state.isErrorName,
            maxLines = 1,
            singleLine = true
        )

        if (state.isErrorName) {
            Text(
                text = stringResource(id = R.string.error_insert_name),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        ButtonComponent(labelText = stringResource(id = R.string.start_quiz)) {
            viewModel.validateName(name = nameUser.value.text){
                if (it) {
                    navigateToQuizScreen(nameUser.value.text)
                }
            }
        }
    }
}

