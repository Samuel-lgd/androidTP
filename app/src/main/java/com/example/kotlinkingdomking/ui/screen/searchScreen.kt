package com.example.kotlinkingdomking.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.kotlinkingdomking.MainViewModel
import com.example.kotlinkingdomking.R
import com.example.kotlinkingdomking.movieAPI.MovieAPI.API_SMALL_IMAGE_URL
import com.example.kotlinkingdomking.movieAPI.MovieBean
import com.example.kotlinkingdomking.ui.theme.LightBlackBackground


@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 24.dp, bottom = 0.dp),
    ) {
        GlideImage(
            model = "https://samuel-lgd.github.io/Neatflex/static/media/neatflex.79be1525c1fc7c2c49e4.png",
            contentDescription = "Neatflex Logo",
            contentScale = ContentScale.FillWidth,
            failure = placeholder(R.drawable.ic_launcher_background),
            modifier = Modifier
                .heightIn(max = 250.dp)
                .widthIn(max = 250.dp)
                .align(CenterHorizontally)
        )
        Spacer(modifier = Modifier.heightIn(12.dp))
        SearchBar(viewModel = viewModel)
        if (viewModel.isLoadInProgress){
            Row(modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                CircularProgressIndicator()
            }
            return
        }
        LazyColumn(content = {
            items(viewModel.moviesList.size) {
                MovieRowCard(data = viewModel.moviesList[it], navHostController = navHostController)
            }
        })
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieRowCard(data: MovieBean, navHostController: NavHostController) {
    Spacer(modifier = Modifier.heightIn(18.dp))
    Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 0.dp)) {
        Row(
            modifier = Modifier
                .heightIn(150.dp)
                .fillMaxWidth()
                .background(Color.White)
                .clickable { navHostController.navigate("detailScreen/${data.id}") }
        ) {
            GlideImage(
                model = API_SMALL_IMAGE_URL + data.poster_path,
                contentDescription = data.name,
                contentScale = ContentScale.FillWidth,
                failure = placeholder(R.drawable.ic_launcher_background),
                modifier = Modifier
                    .heightIn(max = 150.dp)
                    .widthIn(max = 100.dp)
            )
            Column(modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()) {
                Text(text = data.name, fontWeight = FontWeight.Bold)
                Text(text = data.overview, maxLines = 3, fontSize = 14.sp, lineHeight = 16.sp)
                Spacer(modifier = Modifier.heightIn(8.dp))
                Text(text = "Populatiré: " + data.vote_average.toString())
                LinearProgressIndicator(progress = data.vote_average.toFloat() / 10)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    Card( modifier = modifier.fillMaxWidth(), shape = MaterialTheme.shapes.large, colors = CardDefaults.cardColors(
        containerColor = LightBlackBackground
    )
    ) {
        OutlinedTextField(
            value = viewModel.searchText,
            onValueChange = { newValue -> viewModel.updateSearchText(newValue) },
            label = { Text(text = "Rechercher un film, une série...") },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                cursorColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 8.dp,
                    bottom = 0.dp
                ),
        )
        Button(
            onClick = { viewModel.searchMovies() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(Icons.Default.Search, contentDescription = "Search")
            Spacer(modifier = Modifier.widthIn(8.dp))
            Text(text = "Rechercher")
        }
    }

}