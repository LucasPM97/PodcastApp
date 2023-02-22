package com.example.podcast_player_ui.components.fullScreenPlayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.components.SpacerHorizontal
import com.example.core_ui.components.SpacerVertical
import com.example.core_ui.theme.PodcastAppTheme

@Composable
fun PlayerButtons(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    onPlayClicked: (isPlaying: Boolean) -> Unit = {},
    onChangePosition: (secondsToMove: Int) -> Unit = {},
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column {
            SpacerVertical(space = 20)
            IconButton(onClick = {
                onChangePosition(-10)
            }) {
                Icon(
                    imageVector = Icons.Filled.Replay10,
                    contentDescription = "fav episode",
                    Modifier.size(35.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
        SpacerHorizontal(space = 10)
        IconButton(onClick = {
            onPlayClicked(!isPlaying)
        }) {
            Icon(
                imageVector = if (isPlaying) Icons.Filled.Pause
                else Icons.Filled.PlayArrow,
                contentDescription = "fav episode",
                Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        SpacerHorizontal(space = 10)
        Column {
            SpacerVertical(space = 20)
            IconButton(onClick = {
                onChangePosition(10)
            }) {
                Icon(
                    imageVector = Icons.Filled.Forward10,
                    contentDescription = "fav episode",
                    Modifier.size(35.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_PlayerButtons() {
    PodcastAppTheme {
        var isPlaying by remember {
            mutableStateOf(false)
        }
        PlayerButtons(
            isPlaying = isPlaying,
            onPlayClicked = {
                isPlaying = !isPlaying
            }
        )
    }
}