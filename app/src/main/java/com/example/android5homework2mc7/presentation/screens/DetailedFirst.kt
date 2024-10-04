package com.example.android5homework2mc7.presentation.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
    title: String,
    description: String,
    imageResId: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Отображение изображения в Card
        Card(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .size(200.dp),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                focusedElevation = 20.dp
            ),
            border = BorderStroke(3.dp, Color.Black)
        ) {
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = imageResId),
                contentDescription = stringResource(R.string.anime_person)
            )
        }

        // Логирование заголовка для отладки
        Log.e("DetailScreen", "DetailScreen: $title")

        // Отображение заголовка и описания
        Text(text = title, color = Color.Black)
        Text(text = description, color = Color.Gray)

        // Кнопка для возвращения на главный экран
        Button(onClick = { /* Здесь можно добавить логику для возврата */ }) {
            Text(text = "Back to Main Screen")
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        title = "Example Title",
        description = "This is an example description for the detail screen.",
        imageResId = R.drawable.anime_person // Замените на свой ресурс drawable
    )
}
