package com.example.booksharingv2.view.ui_components.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        shape = RoundedCornerShape(0.dp)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
        )
    }
}