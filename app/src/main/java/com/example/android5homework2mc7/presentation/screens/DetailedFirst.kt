package com.example.android5homework2mc7.presentation.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android5homework2mc7.R

@Composable
fun DetailScreen(
    title: String, // Заголовок экрана
    description: String, // Описание места
    imageResId: Int, // Ресурс изображения
    onBackClick: () -> Unit, // Функция для обработки нажатия кнопки "Назад"
) {
    Column(
        modifier = Modifier
            .fillMaxSize() // Заполняет всю доступную область
            .background(Color.White), // Белый фон
        horizontalAlignment = Alignment.CenterHorizontally, // Центрирует содержимое по горизонтали
        verticalArrangement = Arrangement.Center // Центрирует содержимое по вертикали
    ) {
        // Отображение изображения в Card
        Card(
            modifier = Modifier
                .padding(bottom = 20.dp) // Отступ снизу
                .size(200.dp), // Размер карточки
            shape = CircleShape, // Форма карточки - круглая
            elevation = CardDefaults.cardElevation(10.dp), // Эффект тени
            border = BorderStroke(3.dp, Color.Black) // Черная рамка вокруг карточки
        ) {
            Image(
                contentScale = ContentScale.Crop, // Масштабирование изображения
                painter = painterResource(id = imageResId), // Ресурс изображения
                contentDescription = stringResource(R.string.anime_person) // Описание изображения для доступности
            )
        }

        // Логирование заголовка для отладки
        Log.e("DetailScreen", "DetailScreen: $title") // Выводит заголовок в лог для отладки

        // Отображение заголовка и описания
        Text(text = title, color = Color.Black) // Заголовок в черном цвете
        Text(text = description, color = Color.Gray) // Описание в сером цвете

        // Кнопка для возвращения на главный экран
        Button(onClick = onBackClick) { // Обработчик нажатия кнопки
            Text(text = "Back to Main Screen") // Текст кнопки
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    // Предпросмотр экрана деталей с примерными данными
    DetailScreen(
        title = "Example Title", // Пример заголовка
        description = "This is an example description for the detail screen.", // Пример описания
        imageResId = R.drawable.anime_person, // Ресурс изображения для предпросмотра
        onBackClick = {} // Пустая функция для обработки нажатия кнопки в предпросмотре
    )
}
