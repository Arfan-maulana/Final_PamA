package com.example.final_pama

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.final_pama.Model.Movie
import com.example.final_pama.api.ApiRetrofit
import com.example.final_pama.ui.theme.Final_PamATheme
import retrofit2.awaitResponse

class HomeActivity : ComponentActivity() {
    private val api by lazy { ApiRetrofit().apiEndPoint}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Final_PamATheme {
                // A surface container using the 'background' color from the theme
                Column {
                   CustomTopBar()
                    MovieGrid()
                }
            }
        }
    }
    @Composable
    private fun MovieGrid(){
        var movies by remember{ mutableStateOf<List<Movie>>(emptyList()) }

        LaunchedEffect(key1 = Unit){
            getMovies()?.let {
                movies = it
            }
        }

        LazyVerticalGrid(
            columns =GridCells.Fixed(2),
            modifier = Modifier
                .padding(26.dp)

        ){
            items(movies){movie ->
                MovieCard(movie = movie)
            }
        }
    }



    private suspend fun getMovies(): List<Movie>?{
        return try {
            val response = api.getmovie().awaitResponse()
            if (response.isSuccessful){
                response.body()?.movies
            }else{
                null
            }
        } catch (e: Exception){
            null
        }
    }
}




@Composable
private fun CustomTopBar(){
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val isWideScreen = screenWidthDp.dp > 600.dp

    val topBarHeight = if (isWideScreen)118.dp else 50.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(topBarHeight)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(
                    bottomStart = 30.dp,
                    bottomEnd = 30.dp
                )
            ),
        contentAlignment = Alignment.TopCenter
    ){
        Text(
            text = "Movie List",
            style = typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun MovieCard(movie: Movie){
    val context = LocalContext.current

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = ShapeDefaults.Medium,
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    val intent = Intent(context, MovieDetail::class.java)
                    intent.putExtra("movie", movie)
                    context.startActivity(intent)
                }

        ){
            var model: Any = R.drawable.baseline_movie_24
            if (!movie.image.isNullOrEmpty()){
                model = "${Helper.BASE_IMAGE}${movie.image}"
            }
            AsyncImage(model = model,
                contentDescription =null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ){
                Text(
                    text =movie.title,
                    style = typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(text = "Genre : ${movie.genre}", style = typography.bodyMedium)
                //Text(text = "Price : ${movie.price}", style = typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Final_PamATheme {
     CustomTopBar()
    }
}