package com.example.android5homework2mc7.presentation.activity

import FirstScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.android5homework2mc7.presentation.screens.PlaceDetailScreen
import com.example.android5homework2mc7.presentation.screens.ScreenNames
import com.example.android5homework2mc7.presentation.screens.SplashScreen
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Вызов родительского метода для инициализации
        enableEdgeToEdge()  // Включение режима края до края
        setContent { NavController() }  // Установка NavController как содержимого
    }
}

@Composable
fun NavController() {
    var currentScreen by remember { mutableStateOf(ScreenNames.SPLASH_SCREEN) }  // Хранение текущего экрана
    var selectedPlaceDetails by remember { mutableStateOf<Pair<String, Int>?>(null) }  // Хранение деталей места

    LaunchedEffect(Unit) {
        delay(3000)  // Задержка на 3 секунды для SplashScreen
        currentScreen = ScreenNames.FIRST_SCREEN  // Переход на первый экран
    }

    when (currentScreen) {
        ScreenNames.SPLASH_SCREEN -> SplashScreen()  // Отображение SplashScreen
        ScreenNames.FIRST_SCREEN -> FirstScreen { placeTitle, placeImageRes ->
            selectedPlaceDetails =
                placeTitle to placeImageRes  // Сохранение деталей выбранного места
            currentScreen = ScreenNames.DETAIL_SCREEN  // Переход на экран деталей
        }

        ScreenNames.DETAIL_SCREEN -> selectedPlaceDetails?.let { (_, imageRes) ->
            PlaceDetailScreen(
                imageRes,
                onBookingClicked = {
                    currentScreen = ScreenNames.FIRST_SCREEN
                })  // Показ деталей места и установка обработчика кнопки
        }
    }
}
