package com.example.bossguer.features.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bossguer.navigation.Screen
import com.example.bossguer.features.login.presentation.LoginViewModel
import com.example.bossguer.features.login.presentation.SocialProvider
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bossguer.R

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel
) {
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        // Logo circular con imagen
        Box(
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape)
                .background(Color(0xFFF7D7C2)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Boss Guer",
                modifier = Modifier.size(120.dp)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        // Texto principal
        Text(
            text = "¿Ya tienes una\ncuenta?",
            color = Color(0xFF8C1C1C),
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(100.dp))
        // Botón Iniciar sesión
        OutlinedButton(
            onClick = {
                viewModel.onLoginClick()
                navController.navigate(Screen.LoginPart.route)
            },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color(0xFFFFA726)
            ),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                brush = androidx.compose.ui.graphics.Brush.linearGradient(
                    colors = listOf(Color(0xFFFFA726), Color(0xFFFFA726))
                )
            )
        ) {
            Text(
                text = "Iniciar sesión",
                fontSize = 18.sp,
                color = Color(0xFFFFA726),
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        // Separador "o"
        Text(
            text = "o",
            fontSize = 18.sp,
            color = Color(0xFF8C1C1C),
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        // Botón Crear nueva cuenta
        Button(
            onClick = {
                viewModel.onCreateAccountClick()
                navController.navigate(Screen.Registro.route)
            },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFA726),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Crear nueva cuenta",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(80.dp))
        // Iconos sociales
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp)
                .padding(top = 48.dp, bottom = 32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SocialIconDrawable(R.drawable.ic_facebook) { viewModel.onSocialLoginClick(SocialProvider.FACEBOOK) }
            SocialIconDrawable(R.drawable.ic_google) { viewModel.onSocialLoginClick(SocialProvider.GOOGLE) }
            SocialIconDrawable(R.drawable.ic_apple) { viewModel.onSocialLoginClick(SocialProvider.APPLE) }
            SocialIconDrawable(R.drawable.ic_other) { viewModel.onSocialLoginClick(SocialProvider.OTHER) }
        }
        if (loginState.isLoading) {
            Spacer(modifier = Modifier.height(30.dp))
            CircularProgressIndicator()
        }
        loginState.error?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it, color = Color.Red)
        }
    }
}

@Composable
fun SocialIconDrawable(drawableId: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
    }
}
