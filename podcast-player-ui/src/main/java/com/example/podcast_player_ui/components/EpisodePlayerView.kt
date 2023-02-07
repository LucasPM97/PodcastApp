package com.example.podcast_player_ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.mocks.mockEpisode
import com.example.core.models.Episode
import com.example.core_ui.components.AppAsyncImage
import com.example.core_ui.components.SpacerHorizontal20
import com.example.core_ui.extensions.roundedRectangle
import com.example.core_ui.theme.DarkGray
import com.example.core_ui.theme.Green
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_player_ui.PlayerViewModel
import com.example.podcast_player_ui.extensions.dynamicHeight
import com.example.podcast_player_ui.models.ComponentSize
import com.example.podcast_player_ui.models.switchSize
import org.koin.androidx.compose.getViewModel

@Composable
fun EpisodePlayerView(
    defaultSize: ComponentSize,
    viewModel: PlayerViewModel = getViewModel(),
    onSizeChanged: (ComponentSize) -> Unit = {}
) {

    val episode by viewModel.episode.collectAsState(initial = null)

    Content(
        defaultSize,
        episode,
        onSizeChanged
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun Content(
    componentSize: ComponentSize,
    episode: Episode?,
    onSizeChanged: (ComponentSize) -> Unit = {}
) {

    fun switchComponentSize() {
        onSizeChanged(componentSize.switchSize())
    }

    BackHandler(componentSize == ComponentSize.FullScreen) {
        switchComponentSize()
    }

    AnimatedContent(
        modifier = Modifier.background(DarkGray),
        targetState = componentSize,
        transitionSpec = {
            // Compare the incoming number with the previous number.
            if (targetState > initialState) {
                // If the target number is larger, it slides up and fades in
                // while the initial (smaller) number slides up and fades out.
                slideInVertically { height -> height } with
                        slideOutVertically { height -> -height }
            } else {
                // If the target number is smaller, it slides down and fades in
                // while the initial number slides down and fades out.
                slideInVertically { height -> -height } with
                        slideOutVertically { height -> height }
            }.using(
                // Disable clipping since the faded slide-in/out should
                // be displayed out of bounds.
                SizeTransform(clip = false)
            )
        }
    ) {
        Surface(
            color = DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .dynamicHeight(
                    it,
                    smallHeight = 30.dp
                )
        ) {
            when (componentSize) {
                ComponentSize.None -> {}
                ComponentSize.Small -> RowPlayer(episode,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            switchComponentSize()
                        }
                        .padding(bottom = 20.dp, top = 10.dp)
                        .padding(horizontal = 10.dp))
                ComponentSize.FullScreen -> {}
            }
        }
    }
}

@Composable
fun RowPlayer(
    episode: Episode?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppAsyncImage(
                modifier = Modifier
                    .size(70.dp)
                    .roundedRectangle(10.dp),
                imageUrl = episode?.imageUrl ?: "",
                contentDescription = episode?.name ?: "",
            )
            SpacerHorizontal20()
            Column {
                Text(
                    text = episode?.name ?: "",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = episode?.podcastName ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = Green
                )
            }
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.PlayArrow, contentDescription = "play",
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.SkipNext, contentDescription = "play",
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun Preview_Content() {
    PodcastAppTheme {

        var playerSize by remember {
            mutableStateOf(ComponentSize.Small)
        }
        Scaffold(
            bottomBar = {
                Content(
                    episode = mockEpisode(),
                    componentSize = playerSize,
                    onSizeChanged = {
                        playerSize = it
                    }
                )
            }
        ) {
            Column(
                Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {

            }
        }

    }
}
