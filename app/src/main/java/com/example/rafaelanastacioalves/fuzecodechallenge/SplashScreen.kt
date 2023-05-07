package com.example.rafaelanastacioalves.fuzecodechallenge

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.matchlisting.MatchListingActivity
import com.example.rafaelanastacioalves.fuzecodechallenge.ui.theme.RafaelanastacioalvesfuzechallengeTheme
import com.google.android.material.color.MaterialColors
import kotlinx.coroutines.delay

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RafaelanastacioalvesfuzechallengeTheme(darkTheme = true, dynamicColor = false) {
                SplashScreenComposable()
            }
        }


    }

    override fun onResume() {
        super.onResume()
//        startActivity(Intent(this, MatchListingActivity::class.java))
//        finish()
    }
}

@Composable
fun SplashScreenComposable() {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        delay(3000) // 3 segundos de duração
        val intent = Intent(context, MatchListingActivity::class.java)
        context.startActivity(intent)
        (context as Activity).finish()

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.mipmap.fuze_logo),
            contentDescription = null,
            modifier = Modifier.size(113.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RafaelanastacioalvesfuzechallengeTheme {
        SplashScreenComposable()
    }
}
