package com.example.kotlinkingdomking.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.kotlinkingdomking.MainViewModel
import com.example.kotlinkingdomking.R
import com.example.kotlinkingdomking.movieAPI.MovieAPI
import com.example.kotlinkingdomking.movieAPI.SeasonBean

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(movieId: Int, navHostController: NavHostController, viewModel: MainViewModel = viewModel()) {

    LaunchedEffect(Unit){
        viewModel.loadMovieDetails(movieId)
    }
    Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
        if(viewModel.isLoadInProgress){
            Text(text = "Chargement...", color = Color.White)
        }
        GlideImage(
            model = MovieAPI.API_BIG_IMAGE_URL + viewModel.movieDetails.value?.backdrop_path,
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            failure = placeholder(R.drawable.ic_launcher_background),
        )

        Column {
            Text(text = viewModel.movieDetails.value?.name ?: "", color = Color.White, fontSize = 42.sp, fontWeight = FontWeight.Bold)
            Text(text = viewModel.movieDetails.value?.first_air_date.toString().substring(0,4), color = Color.White, fontSize = 14.sp)
            Spacer(modifier = Modifier.heightIn(12.dp))
            Text(text = viewModel.movieDetails.value?.overview ?: "", color = Color.White, fontSize = 18.sp)
            Spacer(modifier = Modifier.heightIn(24.dp))
        }

        Column {
            Row (modifier = Modifier.fillMaxWidth().clickable { viewModel.isSeasonsTabOpen = !viewModel.isSeasonsTabOpen }, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Seasons ",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                )
                if (viewModel.isSeasonsTabOpen) {
                    Text(
                        text = "▲",
                        color = Color.White,
                        fontSize = 28.sp,
                    )
                } else {
                    Text(
                        text = "▼",
                        color = Color.White,
                        fontSize = 28.sp,
                    )
                }
            }
            // J'ai pas mis de lazyRow car avec le verticalScroll de la Column j'ai une erreur
                Row(modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .animateContentSize()) {
                    viewModel.movieDetails.value?.seasons?.forEach {
                        SeasonItem(season = it, isOpen = viewModel.isSeasonsTabOpen)
                    }
                }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SeasonItem(season: SeasonBean, isOpen: Boolean) {
    Column (modifier = Modifier
        .width(222.dp)
        .height(if (isOpen) 600.dp else 0.dp)
        .padding(12.dp)
        .animateContentSize()) {
        GlideImage(
            model = MovieAPI.API_SMALL_IMAGE_URL + season.poster_path,
            contentDescription = "poster",
            contentScale = ContentScale.FillWidth,
            failure = placeholder(R.drawable.ic_launcher_background)
        )
        Text(text = season.name, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(text = season.overview, color = Color.White, fontSize = 18.sp, modifier = Modifier.widthIn(150.dp))
    }
}

