import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.android5homework2mc7.presentation.screens.window.WindowListViewModel

@Composable
fun MainScreen(viewModel: WindowListViewModel, onPlaceCardClick: (String, Int) -> Unit) {
    // Основной экран приложения, который содержит все визуальные компоненты
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Header()  // Вставка заголовка с приветствием и изображением профиля
        SearchBar()  // Вставка поля поиска
        PopularPlacesSection(viewModel) { title, imageResId ->  // Раздел с популярными местами
            onPlaceCardClick(title, imageResId)  // Обработчик нажатия на карточку места
        }
        Spacer(modifier = Modifier.weight(1f))  // Разделитель для пространства между компонентами
        BottomNavigationBar()  // Вставка нижней навигации
    }
}

@Composable
fun PopularPlacesSection(viewModel: WindowListViewModel, onPlaceCardClick: (String, Int) -> Unit) {
    // Секция, показывающая популярные места
    val galereyaList by viewModel.galereyaListState.collectAsState()  // Получение списка популярных мест из ViewModel

    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                "Popular places",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )  // Заголовок секции
            Text("View all", color = Color.Gray)  // Ссылка для просмотра всех мест
        }
        Spacer(modifier = Modifier.height(24.dp))  // Отступ между заголовком и вкладками

        var selectedTab by remember { mutableStateOf("Most Viewed") }  // Текущая выбранная вкладка

        // Вкладки для выбора типа популярных мест
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(listOf("Most Viewed", "Nearby", "Latest", "Window")) { text ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))  // Закругленные края для вкладок
                        .background(if (text == selectedTab) Color.Black else Color.LightGray)  // Изменение фона в зависимости от выбранной вкладки
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                        .clickable {
                            selectedTab = text
                        }  // Обработчик нажатия для изменения выбранной вкладки
                ) {
                    Text(
                        text,
                        color = if (text == selectedTab) Color.White else Color.Gray
                    )  // Текст вкладки
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))  // Отступ перед отображением карточек мест

        // Отображение карточек популярных мест
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(galereyaList) { place ->  // Перебор списка мест
                PlaceCard(
                    title = place.name,  // Название места
                    location = place.country,  // Страна места
                    imageRes = place.image,  // Изображение места
                    rating = "4.8",  // Рейтинг места
                    onClick = {
                        onPlaceCardClick(
                            place.name,
                            place.image
                        )
                    }  // Обработчик нажатия на карточку
                )
            }
        }
    }
}

@Composable
fun PlaceCard(title: String, location: String, imageRes: Int, rating: String, onClick: () -> Unit) {
    // Компонент для отображения карточки с информацией о месте
    Column(
        modifier = Modifier
            .width(170.dp)
            .clickable(onClick = onClick)  // Обработчик нажатия
    ) {
        Image(
            painter = painterResource(id = imageRes),  // Изображение места
            contentDescription = null,
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),  // Закругленные края для изображения
            contentScale = ContentScale.Crop
        )
        Text(title, fontWeight = FontWeight.Bold)  // Название места
        Text(location, color = Color.Gray)  // Локация места
        Text("⭐ $rating", color = Color.Gray, fontSize = 12.sp)  // Рейтинг места
    }
}

@Composable
fun Header() {
    // Заголовок с приветствием и изображением профиля
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Hi, David 👋", fontSize = 28.sp, fontWeight = FontWeight.Bold)  // Приветствие
            Text("Explore the world", fontSize = 16.sp, color = Color.Gray)  // Подзаголовок
        }
        Image(
            painter = painterResource(id = R.drawable.profile_image),  // Изображение профиля
            contentDescription = null,
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .background(Color.LightGray)  // Закругленная форма для изображения профиля
        )
    }
}

@Composable
fun SearchBar() {
    // Поле для ввода поискового запроса
    var searchQuery by remember { mutableStateOf("") }  // Состояние для хранения текста поиска

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
            .background(Color.LightGray, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },  // Обновление текста поиска
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            decorationBox = { innerTextField ->
                if (searchQuery.isEmpty()) Text(
                    "Search places",
                    color = Color.Gray
                )  // Подсказка для ввода
                innerTextField()  // Отображение поля ввода
            }
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_search),  // Иконка поиска
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
        )
    }
}

@Composable
fun BottomNavigationBar() {
    // Нижняя навигационная панель с иконками
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        listOf(
            R.drawable.ic_home,
            R.drawable.ic_clock,
            R.drawable.ic_heart,
            R.drawable.ic_profile
        ).forEach { icon ->  // Иконки навигации
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)  // Размер иконок
            )
        }
    }
}

@Preview
@Composable
fun FirstScreen(onPlaceCardClick: (String, Int) -> Unit) {
    // Предварительный просмотр главного экрана
    MainScreen(viewModel = WindowListViewModel(), onPlaceCardClick = onPlaceCardClick)
}
