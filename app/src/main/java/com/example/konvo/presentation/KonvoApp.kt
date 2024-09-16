package com.example.konvo.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.konvo.data.remote.RetrofitInstance
import com.example.konvo.data.repository.KonvoRepository
import com.example.konvo.presentation.screens.KonvoScreen
import com.example.konvo.presentation.viewmodel.KonvoViewModel
import com.example.konvo.presentation.viewmodel.KonvoViewModelFactory

@Composable
fun KonvoApp(
    modifier: Modifier = Modifier,
    viewModel: KonvoViewModel = viewModel(
        factory = KonvoViewModelFactory(
            KonvoRepository(
                RetrofitInstance.openAIService
            )
        )
    )
) {
    val navController = rememberNavController()

//    NavHost(navController = navController, startDestination = "scenario") {
//        composable("scenario") {
//            ScenarioScreen(
//                viewModel = viewModel,
//                onScenarioSelected = { navController.navigate("conversation") }
//            )
//        }
//        composable("conversation") {
//            KonvoScreen(viewModel = viewModel)
//        }
//    }
}

