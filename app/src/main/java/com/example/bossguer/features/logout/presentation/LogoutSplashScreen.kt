
package com.example.bossguer.features.logout.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun LogoutSplashScreen(onTimeout: () -> Unit) {
    // This effect will run once when the composable enters the screen
    LaunchedEffect(Unit) {
        delay(2000) // Wait for 2 seconds
        onTimeout() // Execute the navigation action
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Cerrando sesión...",
            color = Color.White,
            fontSize = 18.sp
        )
    }
}
