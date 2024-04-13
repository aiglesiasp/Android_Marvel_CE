package com.aiglepub.marvelcompose.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ArrowBackIcon( imageVector: ImageVector,
                   onUpClick: () -> Unit,
                   contentDescription: String? = null
) {
    IconButton(onClick = onUpClick) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}