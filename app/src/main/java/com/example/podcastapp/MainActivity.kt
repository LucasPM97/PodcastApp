package com.example.podcastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_details_ui.screens.podcastDetails.PodcastDetailsScreen
import com.example.podcast_player_ui.components.EpisodePlayerView
import com.example.podcast_player_ui.models.ComponentSize

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PodcastAppTheme {

                var playerSize by remember {
                    mutableStateOf(ComponentSize.None)
                }
                Scaffold(
                    bottomBar = {
                        EpisodePlayerView(
                            playerSize,
                            onSizeChanged = { newSize ->
                                playerSize = newSize
                            }
                        )
                    }
                ) {
                    PodcastDetailsScreen(
                        modifier = Modifier.padding(it),
                        openPodcastPlayer = {
                            playerSize = ComponentSize.Small
                        }
                    )
                }
            }
        }
    }
}