package com.example.podcast_player_ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core_ui.components.AnimatedFade
import com.example.core_ui.theme.DarkGray
import com.example.podcast_player_ui.models.ComponentSize

@Composable
fun DraggablePlayerBoxWithAnimatedContent(
    componentSize: ComponentSize,
    onSizeChanged: (ComponentSize) -> Unit,
    fullScreenPlayer: @Composable BoxScope.() -> Unit = {},
    rowPlayer: @Composable BoxScope.() -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    var draggableBoxHeightInPx by remember {
        mutableStateOf(0)
    }
    val draggableBoxHeight = LocalDensity.current.run { draggableBoxHeightInPx.toDp() }

    val percentageOfScreenFilled = (draggableBoxHeight * 100) / screenHeight

    val backgroundColor by animateColorAsState(
        targetValue = when {
            percentageOfScreenFilled < 20f -> DarkGray
            percentageOfScreenFilled < 40f -> Color(0xFF1D2125)
            percentageOfScreenFilled < 60f -> Color(0xFF191D20)
            percentageOfScreenFilled < 85f -> Color(0xFF15181A)
            else -> MaterialTheme.colorScheme.primary
        },
        animationSpec = tween(durationMillis = 150)
    )

    Surface(
        color = backgroundColor
    ) {
        PlayerDraggableBox(
            modifier = Modifier
                .heightIn(0.dp, screenHeight)
                .fillMaxWidth(),
            componentSize = componentSize,
            screenHeight = screenHeight,
            onComponentSizeChanged = onSizeChanged,
            onHeightChanged = {
                draggableBoxHeightInPx = it
            }
        ) {
//            println("ScreenFilled $percentageOfScreenFilled")
//            println("Box Height $draggableBoxHeight")

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