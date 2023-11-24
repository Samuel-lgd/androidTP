package com.example.kotlinkingdomking.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.kotlinkingdomking.MainViewModel
import com.example.kotlinkingdomking.R
import com.example.kotlinkingdomking.movieAPI.MovieAPI

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(movieId: Int, navHostController: NavHostController, viewModel: MainViewModel = viewModel()) {

    LaunchedEffect(Unit){
        viewModel.loadMovieDetails(movieId)
    }
    Column {
        if(viewModel.isLoadInProgress){
            Text(text = "Chargement...", color = Color.White)
        }
        GlideImage(
            model = MovieAPI.API_BIG_IMAGE_URL + viewModel.movieDetails.value?.backdrop_path,
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            failure = placeholder(R.drawable.ic_launcher_background),
//            modifier = Modifier
//                .heightIn(max = 150.dp)
//                .widthIn(max = 100.dp)
        )

        Column {
            Text(text = viewModel.movieDetails.value?.name ?: "", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text(text = viewModel.movieDetails.value?.first_air_date.toString().substring(0,4), color = Color.White, fontSize = 12.sp)
            Spacer(modifier = Modifier.heightIn(12.dp))
            Text(text = viewModel.movieDetails.value?.overview ?: "", color = Color.White, fontSize = 18.sp)

        }


    }

}

