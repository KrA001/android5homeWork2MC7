
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
    var showDetailScreen by remember { mutableStateOf(false) } // Состояние для показа детального экрана
    var selectedPlace by remember { mutableStateOf<PlaceDetails?>(null) } // Выбранное место для показа деталей

    // Проверка, нужно ли показывать детальный экран
    if (showDetailScreen && selectedPlace != null) {
        DetailScreen(
            title = selectedPlace!!.title,
            location = selectedPlace!!.location,
            imageResId = selectedPlace!!.imageResId,
            onBackClick = { showDetailScreen = false; selectedPlace = null } // Закрыть детальный экран
        )
    } else {
        // Основной экран
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Белый фон
                .padding(24.dp) // Увеличенные отступы
        ) {
            Header() // Заголовок
            SearchBar() // Поисковая строка
            PopularPlacesSection { title, location, imageResId ->
                selectedPlace = PlaceDetails(title, location, imageResId) // Установка выбранного места
                showDetailScreen = true // Показать детальный экран
            }
            Spacer(modifier = Modifier.weight(1f)) // Заполнитель для равномерного распределения пространства
            BottomNavigationBar() // Нижняя навигационная панель
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 24.dp), // Заголовок с отступом сверху
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Hi, David 👋", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black) // Приветствие
            Text("Explore the world", fontSize = 16.sp, color = Color.Gray) // Подзаголовок
        }
        // Изображение профиля
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = null,
            modifier = Modifier.size(45.dp).clip(CircleShape).background(Color.LightGray) // Круглая форма изображения
        )
    }
}

@Composable
fun SearchBar() {
    var searchQuery by remember { mutableStateOf("") } // Состояние для хранения поискового запроса

    // Поле для ввода поискового запроса
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 24.dp)
            .background(Color.LightGray, RoundedCornerShape(12.dp)), // Закругление углов
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it }, // Обновление состояния при изменении текста
            modifier = Modifier.fillMaxWidth().padding(18.dp), // Отступы внутри поля
            decorationBox = { innerTextField ->
                if (searchQuery.isEmpty()) Text("Search places", color = Color.Gray) // Подсказка, если поле пустое
                innerTextField()
            }
        )
        // Иконка поиска
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterEnd).padding(end = 16.dp),
            tint = Color.Black // Цвет иконки
        )
    }
}

@Composable
fun PopularPlacesSection(onPlaceCardClick: (String, String, Int) -> Unit) {
    Column {
        // Заголовок секции популярных мест
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Popular places", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text("View all", color = Color.Gray) // Ссылка на просмотр всех мест
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Вкладки "Most Viewed", "Nearby", "Latest"
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            listOf("Most Viewed", "Nearby", "Latest").forEach { text ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50)) // Закругление углов вкладок
                        .background(if (text == "Most Viewed") Color.Black else Color.LightGray)
                        .padding(horizontal = 24.dp, vertical = 8.dp) // Отступы внутри вкладок
                ) {
                    Text(text, color = if (text == "Most Viewed") Color.White else Color.Gray) // Цвет текста в зависимости от вкладки
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Карточки популярных мест
        Row(modifier = Modifier.fillMaxWidth()) {
            PlaceCard("Mount Fuji, Tokyo", "Tokyo, Japan", R.drawable.mount_fuji, "4.8") {
                onPlaceCardClick("Mount Fuji, Tokyo", "Tokyo, Japan", R.drawable.mount_fuji)
            }
            Spacer(modifier = Modifier.width(16.dp))
            PlaceCard("Andes, South America", "South, America", R.drawable.andes, "4.7") {
                onPlaceCardClick("Andes, South America", "South, America", R.drawable.andes)
            }
        }
    }
}

@Composable
fun PlaceCard(title: String, location: String, imageRes: Int, rating: String, onClick: () -> Unit) {
    // Карточка места
    Column(
        modifier = Modifier.width(170.dp).clickable(onClick = onClick) // Обработка нажатия на карточку
    ) {
        // Изображение места
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.height(220.dp).fillMaxWidth().clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop // Масштабирование изображения
        )
        Text(title, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(top = 8.dp)) // Название места
        Text(location, color = Color.Gray) // Локация места
        Text("⭐ $rating", color = Color.Gray, fontSize = 12.sp) // Рейтинг места
    }
}

@Composable
fun BottomNavigationBar() {
    // Нижняя навигационная панель
    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp), horizontalArrangement = Arrangement.SpaceAround) {
        // Иконки навигации
        listOf(R.drawable.ic_home, R.drawable.ic_clock, R.drawable.ic_heart, R.drawable.ic_profile).forEach { icon ->
            Icon(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier.size(24.dp)) // Размер иконок
        }
    }
}

@Composable
fun DetailScreen(title: String, location: String, imageResId: Int, onBackClick: () -> Unit) {
    // Экран с деталями о месте
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Изображение места на детальном экране
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier.height(240.dp).fillMaxWidth().clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black) // Заголовок
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = location, fontSize = 18.sp, color = Color.Gray) // Локация
        Spacer(modifier = Modifier.height(24.dp))
        // Кнопка "Назад"
        Text(text = "Back", modifier = Modifier.clickable(onClick = onBackClick).padding(16.dp))
    }
}

// Данные о месте
data class PlaceDetails(val title: String, val location: String, val imageResId: Int)

@Preview
@Composable
fun FirstScreen() {
    MainScreen() // Предпросмотр основного экрана
}
