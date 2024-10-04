package com.example.android5homework2mc7.presentation.activity

import FirstScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.android5homework2mc7.R
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Вызывает метод родительского класса
        enableEdgeToEdge() // Включает режим "от края до края" для экрана
        setContent { // Устанавливает содержимое экрана
            SplashScreenApp()
        }
    }
}

@Composable
fun SplashScreenApp() {
    var showSplash by remember { mutableStateOf(true) } // Состояние для отображения SplashScreen

    // Показываем SplashScreen на 3 секунды
    LaunchedEffect(Unit) { // Эффект запускается при первой компоновке
        delay(3000) // Задержка на 3 секунды
        showSplash = false // Скрывает SplashScreen
    }

    // Условное отображение экрана
    if (showSplash) SplashScreen() else FirstScreen() // Отображение SplashScreen или основного экрана
}

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

@Preview
@Composable
fun SecondScreenPreview() {
    SplashScreenApp()
}