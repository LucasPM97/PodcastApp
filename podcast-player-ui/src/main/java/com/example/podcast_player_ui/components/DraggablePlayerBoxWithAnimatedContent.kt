package com.example.podcast_player_ui.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core_ui.components.AnimatedFade
import com.example.podcast_player_ui.models.PlayerSize

@Composable
fun DraggablePlayerBoxWithAnimatedContent(
    playerSize: PlayerSize,
    onSizeChanged: (PlayerSize) -> Unit,
    fullScreenPlayer: @Composable BoxScope.() -> Unit = {},
    rowPlayer: @Composable BoxScope.() -> Unit = {},
    onPlayerClose: () -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp


    PlayerDraggableBox(
        modifier = Modifier
            .heightIn(0.dp, screenHeight)
            .fillMaxWidth(),
        playerSize = playerSize,
        screenHeight = screenHeight,
        onComponentSizeChanged = onSizeChanged,
    ) { percentageOfScreenFilled ->

        LaunchedEffect(percentageOfScreenFilled) {
            if (percentageOfScreenFilled <= 0 && playerSize == PlayerSize.None) {
                onPlayerClose()
            }
        }

        if (percentageOfScreenFilled > PERCENTAGE_OF_SCREEN_WHEN_FULLSREEN_PLAYER_ISVISIBLE) {
            val fullScreenPlayerAlpha =
                calculateFullScreenPlayerAlpha(percentageOfScreenFilled)
            AnimatedFade(alpha = fullScreenPlayerAlpha) {
                fullScreenPlayer()
            }
        }
        if (percentageOfScreenFilled < PERCENTAGE_OF_SCREEN_WHEN_ROW_PLAYER_ISVISIBLE) {
            val rowPlayerAlpha =
                calculateRowPlayerAlpha(screenHeight, percentageOfScreenFilled)

            AnimatedFade(alpha = rowPlayerAlpha) {
                rowPlayer()
            }
        }
    }
}


private const val PERCENTAGE_OF_SCREEN_WHEN_FULLSREEN_PLAYER_ISVISIBLE = 40f
private fun calculateFullScreenPlayerAlpha(percentageOfScreenFilled: Float) =
    (percentageOfScreenFilled - PERCENTAGE_OF_SCREEN_WHEN_FULLSREEN_PLAYER_ISVISIBLE) / PERCENTAGE_OF_SCREEN_WHEN_FULLSREEN_PLAYER_ISVISIBLE


private const val PERCENTAGE_OF_SCREEN_WHEN_ROW_PLAYER_ISVISIBLE = 50f
private fun calculateRowPlayerAlpha(
    screenHeight: Dp,
    percentageOfScreenFilled: Float
): Float {
    val rowPlayerHeight = ROW_PLAYER_HEIGHT
    val screenFilledPercentageByRowPlayer = (rowPlayerHeight * 100) / screenHeight
    return (PERCENTAGE_OF_SCREEN_WHEN_ROW_PLAYER_ISVISIBLE - percentageOfScreenFilled) / (PERCENTAGE_OF_SCREEN_WHEN_ROW_PLAYER_ISVISIBLE - screenFilledPercentageByRowPlayer)
}