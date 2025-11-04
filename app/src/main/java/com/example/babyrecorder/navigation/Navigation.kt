package com.example.babyrecorder.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.babyrecorder.ui.home.HomeScreen
import com.example.babyrecorder.ui.stats.StatsScreen
import com.example.babyrecorder.ui.settings.SettingsScreen
import com.example.babyrecorder.viewmodel.MainViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Stats : Screen("stats")
    object Settings : Screen("settings")
}

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(viewModel)
            }
            composable(Screen.Stats.route) {
                StatsScreen(viewModel)
            }
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "首页") },
            label = { Text("首页") },
            selected = navController.currentDestination?.route == Screen.Home.route,
            onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
        
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Analytics, contentDescription = "统计") },
            label = { Text("统计") },
            selected = navController.currentDestination?.route == Screen.Stats.route,
            onClick = {
                navController.navigate(Screen.Stats.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
        
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "设置") },
            label = { Text("设置") },
            selected = navController.currentDestination?.route == Screen.Settings.route,
            onClick = {
                navController.navigate(Screen.Settings.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
    }
}