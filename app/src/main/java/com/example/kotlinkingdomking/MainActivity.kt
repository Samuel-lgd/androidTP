package com.example.kotlinkingdomking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kotlinkingdomking.ui.screen.DetailScreen
import com.example.kotlinkingdomking.ui.screen.SearchScreen
import com.example.kotlinkingdomking.ui.theme.BlackBackground
import com.example.kotlinkingdomking.ui.theme.KotlinKingdomKingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinKingdomKingTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = BlackBackground) {
//                    AppNavigation()
                    val navController: NavHostController = rememberNavController()
        DetailScreen(movieId = 66732 , navHostController = navController)
                }
            }
        }
    }

    @Composable
    fun AppNavigation() {

        val navController: NavHostController = rememberNavController()
        val viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

        NavHost(navController = navController, startDestination = Routes.SearchScreen.route) {
            composable(Routes.SearchScreen.route) {
                SearchScreen(navHostController = navController)
            }
            composable(
                route = Routes.DetailScreen.route,
                arguments = listOf(navArgument("data") { type = NavType.IntType })
            ) {
                val position = it.arguments?.getInt("data", 0) ?: 0
                DetailScreen(movieId = position, navHostController = navController)
            }
        }
    }
}