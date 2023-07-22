package com.example.quizdynamox.ui.screens.load

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizdynamox.R
import com.valentinilk.shimmer.shimmer

@Preview(showSystemUi = true)
@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Question()

        Options()

    }
}

@Composable
private fun Options() {
    Spacer(modifier = Modifier.padding(top = 12.dp))
    Column(
        modifier = Modifier
            .defaultMinSize(minHeight = 380.dp, minWidth = 380.dp)
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.answer),
            modifier = Modifier.padding(vertical = 10.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
        
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(450.dp)
                .padding(horizontal = 12.dp)
                .shimmer()
                .background(Color.DarkGray)
        )
    }
}

@Composable
private fun Question() {
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .defaultMinSize(minHeight = 150.dp)
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.question),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.surface
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(90.dp)
                .padding(horizontal = 12.dp)
                .shimmer()
                .background(Color.DarkGray)
        )


        Spacer(modifier = Modifier.padding(vertical = 10.dp))
    }
}
