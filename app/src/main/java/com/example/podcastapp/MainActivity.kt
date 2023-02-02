package com.example.podcastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_details_ui.screens.podcastDetails.PodcastDetailsScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PodcastAppTheme {
                PodcastDetailsScreen()
            }
        }
    }
}