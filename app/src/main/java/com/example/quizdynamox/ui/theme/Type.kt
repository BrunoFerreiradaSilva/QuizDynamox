package com.example.quizdynamox.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.quizdynamox.R

private val Montserrat = FontFamily(
    Font(R.font.montserrat_light, FontWeight.W300)
)

private val Century = FontFamily(
    Font(R.font.century_gothic, FontWeight.W400)
)


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Century,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)