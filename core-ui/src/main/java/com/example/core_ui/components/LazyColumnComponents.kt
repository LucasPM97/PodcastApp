package com.example.core_ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier

fun LazyListScope.fillLazyColumn(
    spaceAlreadyOccupiedInPx: Float,
    listHeightInPx: Float
) {

    fun getNeededFractionToFillParent(
        spaceAlreadyOccupiedInPx: Float,
        listHeightInPx: Float
    ): Float {
        //If there's no space to fill, returns 0
        if (spaceAlreadyOccupiedInPx >= listHeightInPx) return 0f

        val spaceAlreadyOccupiedFraction = spaceAlreadyOccupiedInPx / listHeightInPx
        return 1 - spaceAlreadyOccupiedFraction
    }

    item {
        Spacer(
            modifier = Modifier
                .fillParentMaxHeight(
                    getNeededFractionToFillParent(
                        spaceAlreadyOccupiedInPx,
                        listHeightInPx = listHeightInPx
                    )
                )
        )
    }
}