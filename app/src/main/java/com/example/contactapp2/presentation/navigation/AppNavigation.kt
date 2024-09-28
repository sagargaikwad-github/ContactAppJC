package com.example.contactapp2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.contactapp2.ContactAppViewModel
import com.example.contactapp2.presentation.screen.AddEditScreenUI
import com.example.contactapp2.presentation.screen.HomeScreenUI


@Composable
fun AppNavigation(modifier: Modifier = Modifier, viewModel: ContactAppViewModel) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeScreen) {

        composable<HomeScreen> {
            HomeScreenUI(navController, viewModel)
        }

        composable<AddEditScreen> {
            val addEditScreen = it.toRoute<AddEditScreen>()
            AddEditScreenUI(navController, viewModel, addEditScreen.id)
        }

    }

}

