package com.example.podcastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_details_ui.screens.podcastDetails.PodcastDetailsScreen
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PodcastAppTheme {
                val bottomSheetNavigator = rememberBottomSheetNavigator()
                val navController = rememberNavController(bottomSheetNavigator)
                ModalBottomSheetLayout(bottomSheetNavigator) {
                    NavHost(navController, "home") {
                        composable(route = "home") {
                            PodcastDetailsScreen(
                                openPodcastPlayer = {
                                    navController.navigate("sheet")
                                }
                            )
                        }
                        bottomSheet(route = "sheet") {
                            Text("This is a cool bottom sheet!")
                        }
                    }
                }
            }
        }
    }
}