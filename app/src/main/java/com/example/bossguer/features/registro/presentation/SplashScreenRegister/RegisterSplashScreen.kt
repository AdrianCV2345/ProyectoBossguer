package com.example.bossguer.features.registro.presentation.SplashScreenRegister

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bossguer.R // Asegúrate de que esta sea la ruta correcta a tus recursos
import kotlinx.coroutines.delay

@Composable
fun RegisterSplashScreen(
    onNavigateToMenu: () -> Unit
) {
    // LaunchedEffect se ejecuta una sola vez cuando el composable entra en la pantalla.
    // Es el lugar perfecto para lógicas de "ejecutar una vez", como una redirección.
    LaunchedEffect(key1 = true) {
        // 1. Espera 2 segundos para que el usuario pueda leer el mensaje.
        delay(2000L) // 2000 milisegundos = 2 segundos

        // 2. Llama a la función de navegación para ir al menú.
        onNavigateToMenu()
    }

    // --- UI de la pantalla ---
    // Un fondo oscuro y un contenido centrado.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E)), // Fondo oscuro, puedes cambiarlo
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ícono de check (éxito)
        Image(
            painter = painterResource(id = R.drawable.logo), // Reemplaza con tu ícono de check
            contentDescription = "Registro Exitoso",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Texto de confirmación
        Text(
            text = "¡Registrado Correctamente!",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// --- Vista Previa para Desarrollo ---
// Esto te permite ver cómo se ve tu pantalla en el editor de Android Studio.
@Preview(showBackground = true)
@Composable
fun RegisterSplashScreenPreview() {
    RegisterSplashScreen(onNavigateToMenu = {}) // En la preview, la navegación no hace nada.
}
