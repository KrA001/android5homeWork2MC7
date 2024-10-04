package com.example.android5homework2mc7.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.android5homework2mc7.R

@Composable
fun PlaceDetailScreen(imageRes: Int, onBookingClicked: () -> Unit) {
    // Главный экран с деталями о месте
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))  // Отступ сверху

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))  // Скругление углов
                .background(Color.Gray)  // Фоновый цвет
                .fillMaxWidth()
        ) {
            // Отображение изображения места
            Image(
                painter = painterResource(id = imageRes),  // Получение изображения по ресурсу
                contentDescription = null,
                contentScale = ContentScale.Crop,  // Обработка масштаба изображения
                modifier = Modifier.height(320.dp).fillMaxWidth()  // Задание размеров
            )

            // Кнопка "Назад"
            Row(
                modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)  // Размер кнопки
                        .clip(CircleShape)  // Кнопка с закругленными углами
                        .background(Color.Black.copy(alpha = 0.5f))  // Полупрозрачный фон кнопки
                        .clickable { /* Обработка нажатия на кнопку "Назад" */ }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,  // Иконка "Назад"
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp).align(Alignment.Center)  // Размер иконки
                    )
                }
            }

            // Текст поверх изображения
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(Color.Black.copy(alpha = 0.5f))  // Полупрозрачный фон текста
                    .padding(16.dp)
            ) {
                Text("Andes Mountain", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 25.sp)  // Название места
                Text("South, America", color = Color.White, fontSize = 14.sp)  // Локация
                Text("$230", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)  // Цена
            }
        }

        Spacer(modifier = Modifier.height(36.dp))  // Увеличенный отступ между изображением и остальным контентом

        // Вызов функции для отображения иконок с подписями
        IconRow()

        // Названия "Overview" и "Details"
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween  // Распределение по обеим сторонам
        ) {
            OverviewDetailsText("Overview")  // Заголовок "Обзор"
            OverviewDetailsText("Details")   // Заголовок "Детали"
        }

        // Описание места
        Text(
            text = "This vast mountain range is renowned for its remarkable diversity in terms of topography and climate. It features towering peaks, active volcanoes, deep canyons, expansive plateaus...",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 16.dp)  // Отступы
        )

        Spacer(modifier = Modifier.weight(1f))  // Отступ для равномерного распределения пространства

        // Кнопка "Забронировать"
        Button(
            onClick = onBookingClicked,  // Обработка нажатия на кнопку
            modifier = Modifier.fillMaxWidth().padding(16.dp),  // Заполнение ширины
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),  // Цвет кнопки
            shape = RoundedCornerShape(16.dp)  // Скругление углов кнопки
        ) {
            Text("Book Now", color = Color.White)  // Текст на кнопке
        }
    }
}

@Composable
fun OverviewDetailsText(text: String) {
    // Текст для названий "Overview" и "Details" с фоновым цветом
    Box(
        modifier = Modifier
            .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(8.dp))  // Фон с закругленными углами
            .padding(8.dp)  // Отступы
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black  // Цвет текста
        )
    }
}

@Composable
fun IconRow() {
    // Строка с иконками и подписями
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),  // Горизонтальные отступы
        horizontalArrangement = Arrangement.SpaceAround,  // Распределение по горизонтали
        verticalAlignment = Alignment.CenterVertically  // Выравнивание по вертикали
    ) {
        IconWithLabel(Icons.Filled.Info, "8 hours")  // Иконка информации
        IconWithLabel(Icons.Filled.Notifications, "16:00")  // Иконка уведомлений
        IconWithLabel(Icons.Filled.Star, "4.5")  // Иконка рейтинга
    }
}

@Composable
fun IconWithLabel(icon: ImageVector, label: String) {
    // Компонент для отображения иконки и подписи
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,  // Выравнивание по центру
        modifier = Modifier.padding(8.dp)  // Внутренний отступ
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.Black, modifier = Modifier.size(32.dp))  // Размер иконки
        Text(text = label, fontSize = 14.sp, color = Color.Black)  // Текст под иконкой
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlaceDetailScreen() {
    PlaceDetailScreen(imageRes = R.drawable.anime_person, onBookingClicked = {})  // Предпросмотр экрана с примерным изображением
}
