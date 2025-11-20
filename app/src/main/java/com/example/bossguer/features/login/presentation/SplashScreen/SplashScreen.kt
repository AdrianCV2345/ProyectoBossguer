package com.example.bossguer.features.login.presentation.SplashScreen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bossguer.R
import com.example.bossguer.ui.theme.BossguerTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigate: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }

    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    val scaleAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.8f,
        animationSpec = tween(durationMillis = 1000)
    )

    var startExitAnimation by remember { mutableStateOf(false) }

    val offsetYAnim by animateDpAsState(
        targetValue = if (startExitAnimation) (-100).dp else 0.dp,
        animationSpec = tween(durationMillis = 500)
    )
    val scaleDownAnim by animateFloatAsState(
        targetValue = if (startExitAnimation) 0.8f else 1f,
        animationSpec = tween(durationMillis = 500)
    )

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(2000) // Wait 2 seconds
        startExitAnimation = true
        delay(700) // Wait for exit animation to finish
        onNavigate()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // 1. Añade tu fondo a: app/src/main/res/drawable
        // 2. Reemplaza \"your_background_image\" con el nombre de tu archivo.
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "Fondo de hamburguesa",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .offset(y = offsetYAnim)
                .scale(scaleAnim * scaleDownAnim)
                .alpha(alphaAnim),
            contentAlignment = Alignment.Center
        ) {
            BossGuerLogo()
        }
    }
}

@Composable
fun BossGuerLogo() {
    val orangeColor = Color(0xFFF9A825)

    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(orangeColor),
        contentAlignment = Alignment.Center
    ) {
        // 1. Añade tu logo a: app/src/main/res/drawable
        // 2. Reemplaza \"your_logo_image\" con el nombre de tu archivo.
        Image(
            painter = painterResource(id = R.drawable.logosplash),
            contentDescription = "Logo BossGuer",
            modifier = Modifier.size(90.dp) // Ajusta el tamaño del logo dentro del círculo
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    BossguerTheme {
        SplashScreen(onNavigate = {})
    }
}

@Preview(showBackground = true)
@Composable
fun BossGuerLogoPreview() {
    BossguerTheme {
        Box(modifier = Modifier.fillMaxSize().background(Color.Gray), contentAlignment = Alignment.Center) {
            BossGuerLogo()
        }
    }
}
