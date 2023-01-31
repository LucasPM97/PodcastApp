package com.example.podcastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.podcast_details_domain.useCases.GetPodcastDetailsUseCase
import com.example.core_ui.theme.PodcastAppTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    val getPodcastDetails: GetPodcastDetailsUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            com.example.core_ui.theme.PodcastAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

        lifecycleScope.launchWhenResumed {
            val podcastDetails = getPodcastDetails("")
            val podcastFound = podcastDetails != null
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    com.example.core_ui.theme.PodcastAppTheme {
        Greeting("Android")
    }
}