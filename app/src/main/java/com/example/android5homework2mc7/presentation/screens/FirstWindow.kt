package com.example.jetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android5homework2mc7.R

@Composable
fun MainScreen() {
    var showDetailScreen by remember { mutableStateOf(false) }
    var selectedPlace by remember { mutableStateOf<PlaceDetails?>(null) }

    if (showDetailScreen && selectedPlace != null) {
        DetailScreen(
            title = selectedPlace!!.title,
            location = selectedPlace!!.location,
            imageResId = selectedPlace!!.imageResId,
            onBackClick = {
                showDetailScreen = false
                selectedPlace = null
            }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Белый фон
                .padding(24.dp) // Увеличенные отступы
        ) {
            Header()
            SearchBar()
            PopularPlacesSection(onPlaceCardClick = { title, location, imageResId ->
                selectedPlace = PlaceDetails(title, location, imageResId)
                showDetailScreen = true
            })
            Spacer(modifier = Modifier.weight(1f))
            BottomNavigationBar()
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp), // Отступ сверху
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Hi, David 👋", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black) // Изменен цвет текста
            Text("Explore the world", fontSize = 16.sp, color = Color.Gray)
        }
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = null,
            modifier = Modifier
                .size(45.dp) // Увеличенный размер изображения профиля
                .clip(CircleShape)
                .background(Color.LightGray)
        )
    }
}

@Composable
fun SearchBar() {
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp) // Увеличенный отступ
            .background(Color.LightGray, RoundedCornerShape(12.dp)), // Закругление углов
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp), // Отступ внутри
            decorationBox = { innerTextField ->
                if (searchQuery.isEmpty()) {
                    Text("Search places", color = Color.Gray)
                }
                innerTextField()
            }
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp),
            tint = Color.Black // Изменен цвет иконки
        )
    }
}

@Composable
fun PopularPlacesSection(onPlaceCardClick: (String, String, Int) -> Unit) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Popular places", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black) // Изменен цвет текста
            Text("View all", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Вкладки "Most Viewed", "Nearby", "Latest"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50)) // Закругление кнопок
                    .background(Color.Black)
                    .padding(horizontal = 24.dp, vertical = 8.dp) // Отступы внутри кнопок
            ) {
                Text("Most Viewed", color = Color.White)
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color.LightGray)
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            ) {
                Text("Nearby", color = Color.Gray)
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color.LightGray)
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            ) {
                Text("Latest", color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            PlaceCard(
                title = "Mount Fuji, Tokyo",
                location = "Tokyo, Japan",
                imageRes = R.drawable.mount_fuji,
                rating = "4.8",
                onClick = { onPlaceCardClick("Mount Fuji, Tokyo", "Tokyo, Japan", R.drawable.mount_fuji) }
            )
            Spacer(modifier = Modifier.width(16.dp))
            PlaceCard(
                title = "Andes, South America",
                location = "South, America",
                imageRes = R.drawable.andes,
                rating = "4.7",
                onClick = { onPlaceCardClick("Andes, South America", "South, America", R.drawable.andes) }
            )
        }
    }
}

@Composable
fun PlaceCard(title: String, location: String, imageRes: Int, rating: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(170.dp) // Ширина карточек
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .height(220.dp) // Высота изображения
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)), // Закругленные углы
            contentScale = ContentScale.Crop
        )
        Text(title, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(top = 8.dp)) // Изменен цвет текста
        Text(location, color = Color.Gray)
        Text("⭐ $rating", color = Color.Gray, fontSize = 12.sp)
    }
}

@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_home),
            contentDescription = "Home",
            modifier = Modifier.size(24.dp) // Увеличенный размер иконок
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_clock),
            contentDescription = "Recent",
            modifier = Modifier.size(24.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_heart),
            contentDescription = "Favorites",
            modifier = Modifier.size(24.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Profile",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun DetailScreen(title: String, location: String, imageResId: Int, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black) // Изменен цвет текста
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = location, fontSize = 18.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Back",
            modifier = Modifier
                .clickable(onClick = onBackClick)
                .padding(16.dp)
        )
    }
}

data class PlaceDetails(
    val title: String,
    val location: String,
    val imageResId: Int
)

@Preview
@Composable
fun FirstScreen() {
    MainScreen()
}
