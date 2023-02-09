package com.example.podcast_player_ui.components.fullScreenPlayer

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core.models.Episode
import com.example.core_ui.theme.Green

@Composable
 fun TitleWithFav(episode: Episode?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.width(30.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = episode?.name ?: "",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
            Text(
                text = episode?.podcastName ?: "",
                style = MaterialTheme.typography.titleSmall,
                color = Green,
                textAlign = TextAlign.Center,
                maxLines = 1

            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "fav episode",
                Modifier.size(30.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}