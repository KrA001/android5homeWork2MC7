package com.example.android5homework2mc7.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.android5homework2mc7.R

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize() // Заполняет всю доступную область
            .background(Color.Black), // Устанавливает черный фон
        contentAlignment = Alignment.Center // Центрирует содержимое по центру
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_travel_logo), // Загружает изображение из ресурсов
            contentDescription = null, // Описание изображения для доступности
            modifier = Modifier.fillMaxSize() // Заполняет всю область изображения
        )
    }
}