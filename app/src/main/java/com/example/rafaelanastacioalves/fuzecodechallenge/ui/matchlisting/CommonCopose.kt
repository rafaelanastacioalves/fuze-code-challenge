package com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    noavigationUpIcon: @Composable() (() -> Unit)? = null,
    textTitleComposable: @Composable () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp,
        title = textTitleComposable,
        navigationIcon = noavigationUpIcon,

        )
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(align = Alignment.Center)) {
        CircularProgressIndicator()
    }
}