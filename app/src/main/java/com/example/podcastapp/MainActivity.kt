package com.example.podcastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_details_ui.screens.podcastDetails.PodcastDetailsScreen
import com.example.podcast_player_ui.EpisodePlayerView
import com.example.podcast_player_ui.PlayerViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PodcastAppTheme {

                val playerViewModel: PlayerViewModel = getViewModel()

                // Remove after removing Mock data
                val episode by playerViewModel.episode.collectAsState(initial = null)

                Scaffold(
                    bottomBar = {
                        // Switch after removing Mock data to:  episode?.let {
                        episode?.let {
                            EpisodePlayerView(
                                episode = episode,
                                clearPlaylist = {
                                    playerViewModel.clearPlaylist()
                                }
                            )
                        }
                    }
                ) {
                    PodcastDetailsScreen(
                        modifier = Modifier.padding(it),
                        openPodcastPlayer = { episodeUuid ->
                            playerViewModel.playEpisode(episodeUuid)
                        }
                    )
                }
            }
        }
    }
}